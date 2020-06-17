package hcmute.edu.vn.group11.foody.entities;

public class Discount {
    int id;
    String name;
    int percent;

    public Discount() {
    }

    public Discount(int id, String name, int percent) {
        this.id = id;
        this.name = name;
        this.percent = percent;
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

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
