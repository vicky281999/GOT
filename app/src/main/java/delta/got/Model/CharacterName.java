package delta.got.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharacterName {

    @SerializedName("data")
    @Expose
    private delta.got.Model.data data;


    public CharacterName( delta.got.Model.data data) {
        super();
        this.data = data;
    }


    public delta.got.Model.data getData() {
        return data;
    }

    public void setData(delta.got.Model.data data) {
        this.data = data;
    }

}

