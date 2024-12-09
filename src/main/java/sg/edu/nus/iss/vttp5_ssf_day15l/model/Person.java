package sg.edu.nus.iss.vttp5_ssf_day15l.model;

public class Person {
    
    // practice - put in the necessary validations 

    private Integer id;
    private String fullName;
    private String email;
    
    public Person() {

    }
    
    public Person(Integer id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id + "," + fullName + "," + email;
    }

    
}