package basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Group extends Artist implements Serializable
{

    ArrayList<Person> members = new ArrayList<>();

    public Group(String name, String gender, String country, String beginCity, String endCity, Date birthDate, Date deathDate, ArrayList<Aliases> aliases, ArrayList<Tag> tags, String id, ArrayList<Person> members)
    {
        super(name, gender, country, beginCity, endCity, birthDate, deathDate, aliases, tags, id);
        this.members = members;
    }

}
