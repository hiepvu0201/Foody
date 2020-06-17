package hcmute.edu.vn.group11.foody.entities;

public class Restaurant {

    private String Name;
    private String FoodName;
    private String Description;
    private int Thumbnail;

    public Restaurant() {
    }

    public Restaurant(String name, String foodName, String description, int thumbnail) {
        Name = name;
        FoodName = foodName;
        Description = description;
        Thumbnail = thumbnail;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
