package delta.got.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class data {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("imageLink")
    @Expose
    private String imageLink;

    @SerializedName("spouse")
    @Expose
    private String spouse;

    @SerializedName("culture")
    @Expose
    private String culture;
    @SerializedName("house")
    @Expose
    private String house;



    @SerializedName("books")
    @Expose
    private List<String> books = null;

    @SerializedName("titles")
    @Expose
    private List<String> titles = null;


    public data() {
    }

    public data(String imageLink, String spouse, String culture, String house, String name, List<String> books, List<String> titles) {
        super();
        this.imageLink = imageLink;
        this.spouse = spouse;
        this.culture = culture;
        this.house = house;
        this.name = name;
        this.books = books;
        this.titles = titles;
    }




    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }


    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

}

