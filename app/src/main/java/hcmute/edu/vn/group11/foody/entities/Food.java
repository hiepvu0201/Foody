package hcmute.edu.vn.group11.foody.entities;

public class Food {
    private  int id;
    private String name;
    private String image;
    private String Gia;
    private int idQuan;

    public Food() {
    }

    public Food(int id, String name, String image, String gia, int idQuan) {
        this.id = id;
        this.name = name;
        this.image = image;
        Gia = gia;
        this.idQuan = idQuan;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public int getIdQuan() {
        return idQuan;
    }

    public void setIdQuan(int idQuan) {
        this.idQuan = idQuan;
    }
}
