package basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public abstract class Artist implements Serializable {

    private String name, country, beginCity, endCity, id;
    private Date birthDate, deathDate;
    private ArrayList<Tag> tags;
    private ArrayList<Aliases> aliases;

    public Artist() {

    }

    public Artist(String name, String gender, String country, String beginCity, String endCity, Date birthDate, Date deathDate, ArrayList<Aliases> aliases, ArrayList<Tag> tags, String id) {
        this.name = name;
        this.country = country;
        this.beginCity = beginCity;
        this.endCity = endCity;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.aliases = aliases;
        this.tags = tags;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        if (country == null) {
            return "";
        }
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBeginCity() {
        if (beginCity == null) {
            return "Not Found";
        }
        return beginCity;
    }

    public void setBeginCity(String beginCity) {
        this.beginCity = beginCity;
    }

    public String getEndCity() {
        if (endCity == null) {
            return "Not Found";
        }
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public ArrayList<String> getAliases() {
        ArrayList<String> stringAliases = new ArrayList();
        if (aliases != null) {
            for (int i = 0; i < aliases.size(); i++) {
                stringAliases.add(aliases.get(i).getName());
            }
            return stringAliases;
        } else {
            stringAliases.add("No Aliases Found");
        }
        return stringAliases;
    }

    public void setAliases(ArrayList<Aliases> aliases) {
        this.aliases = aliases;
    }

    public ArrayList<String> getTags() {
        ArrayList<String> stringTags = new ArrayList();
        if (tags != null) {
            for (int i = 0; i < tags.size(); i++) {
                stringTags.add(tags.get(i).getName());
            }
            return stringTags;
        } else {
            stringTags.add("No Tags Found");
        }
        return stringTags;

    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
