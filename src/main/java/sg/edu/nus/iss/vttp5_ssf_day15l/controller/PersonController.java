package sg.edu.nus.iss.vttp5_ssf_day15l.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.vttp5_ssf_day15l.model.Person;
import sg.edu.nus.iss.vttp5_ssf_day15l.service.PersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("")
    public String getPersonList(Model model){
        List<Person> personList = personService.findAll("personList");
        model.addAttribute("personList", personList);
        return "personlist";
    }

    @GetMapping("/create")
    public String createPerson(Model model){
        Person person = new Person();
        model.addAttribute("person", person);
        return "personcreate";
    }

    @PostMapping("/create")
    public String createPersonPost(@ModelAttribute Person person){
        personService.addPerson("personList", person);
        return "redirect:/persons";
    }

    @GetMapping("/delete")
    public String deletePerson(@RequestParam Integer id){
        personService.delete("personList", id);
        return "redirect:/persons";
    }
}



//person list page

//person create

//person delete
