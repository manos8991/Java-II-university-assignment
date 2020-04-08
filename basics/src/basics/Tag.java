package basics;

import java.io.Serializable;

public class Tag implements Serializable {

    private String name;
    private int count;


  
    
    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

}
