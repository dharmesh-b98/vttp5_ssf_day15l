package sg.edu.nus.iss.vttp5_ssf_day15l.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp5_ssf_day15l.constant.Constants;

@Repository
public class ListRepo {

    @Autowired
    @Qualifier(Constants.template01) //DARYL put template01 here also???
    RedisTemplate<String, String> template;

    // slide 30, slide 34
    public void leftPush(String key, String value) {
        template.opsForList().leftPush(key, value);
    }
    
    public void rightPush(String key, String value) {
        template.opsForList().rightPush(key, value);
    }

    // slide 30
    public void leftPop(String key) {
        template.opsForList().leftPop(key, 1);
    }

    // slide 32
    public String get(String key, Integer index) {
        return template.opsForList().index(key, index).toString();
    }

    // slide 33
    public Long size(String key) {
        return template.opsForList().size(key);
    }

    public List<String> getList(String key) {
        List<String> list = template.opsForList().range(key, 0, -1);

        return list;
    }


    public Boolean deleteItem(String key,Integer id){
        
        Boolean isDeleted = false;
        List<String> retrievedList = template.opsForList().range(key,0,-1);//each entry is a comma separated string
       
 
        Optional<String> entryIfFound = retrievedList.stream().filter(a->a.contains(String.valueOf(id))).findFirst(); //string entry of person if found

        if (entryIfFound.isPresent()){
            String entryIfFoundString = entryIfFound.get();
            template.opsForList().remove(key,0,entryIfFoundString);
            isDeleted = true;
        }

        return isDeleted;
    }
}