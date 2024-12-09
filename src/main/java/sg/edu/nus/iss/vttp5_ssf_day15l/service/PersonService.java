package sg.edu.nus.iss.vttp5_ssf_day15l.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vttp5_ssf_day15l.model.Person;
import sg.edu.nus.iss.vttp5_ssf_day15l.repo.ListRepo;

@Service
public class PersonService {
    
    @Autowired
    private ListRepo personRepo;

    public void addPerson(String key,Person person){
        String personString = person.toString();
        personRepo.rightPush(key,personString);
    }

    public boolean delete(String key, Integer id){
        return personRepo.deleteItem(key, id);
    }

    public List<Person> findAll(String key){
        List<String> personListString = personRepo.getList(key);
        List<Person> personList = new ArrayList<>();
        for (String personString: personListString){
            String[] personStringAttributeList = personString.split(",");
            Integer id = Integer.parseInt(personStringAttributeList[0]);
            String fullName = personStringAttributeList[1];
            String email = personStringAttributeList[2];
            Person person= new Person(id,fullName,email);
            personList.add(person);
        }
        return personList;
        
    }
}
