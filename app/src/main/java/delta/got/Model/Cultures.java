package delta.got.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cultures {

    @SerializedName("name")
    @Expose
    private String name;



    public Cultures() {
    }


    public Cultures(String name) {
        super();
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
