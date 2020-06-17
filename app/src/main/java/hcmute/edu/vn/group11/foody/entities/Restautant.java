package hcmute.edu.vn.group11.foody.entities;

public class Restautant {
    int id;
    String name;
    String description;
    String imagepath;
    String address;

    public Restautant() {
    }

    public Restautant(int id, String name, String description, String imagepath, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagepath = imagepath;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
