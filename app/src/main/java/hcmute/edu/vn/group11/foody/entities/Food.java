package hcmute.edu.vn.group11.foody.entities;

public class Food {
    private  int id;
    private String name;
    private String image;
    private int idQuan;
    private String Gia;

    public Food() {
    }

    public Food(int id, String name, String image, int idQuan, String gia) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.idQuan = idQuan;
        Gia = gia;
    }

    public int getIdQuan() {
        return idQuan;
    }

    public void setIdQuan(int idQuan) {
        this.idQuan = idQuan;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
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
}
