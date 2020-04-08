package basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Person extends Artist implements Serializable {

    private String gender;

    public Person() {

    }

    public Person(String name, String gender, String country, String beginCity, String endCity, Date birthDate, Date deathDate, ArrayList<Aliases> aliases, ArrayList<Tag> tags, String id) {
        super(name, gender, country, beginCity, endCity, birthDate, deathDate, aliases, tags, id);
    }

    public String getGender() {
        if (gender == null) {
            return "Not Found";
        }
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.getName() + ", " + this.getCountry();
    }
}
