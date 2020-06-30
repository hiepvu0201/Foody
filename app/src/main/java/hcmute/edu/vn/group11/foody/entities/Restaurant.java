package hcmute.edu.vn.group11.foody.entities;

public class Restaurant {
    private int id;
    private String name;
    private String description;
    private String phone;
    private String wifi;
    private String wifipass;
    private String type;
    private String maxprice;
    private String minprice;
    private String image;
    private String province;
    private String address;

    public Restaurant() {
    }

    public Restaurant(int id, String name, String description, String phone, String wifi, String wifipass, String type, String maxprice, String minprice, String image, String province, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.wifi = wifi;
        this.wifipass = wifipass;
        this.type = type;
        this.maxprice = maxprice;
        this.minprice = minprice;
        this.image = image;
        this.province = province;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getWifipass() {
        return wifipass;
    }

    public void setWifipass(String wifipass) {
        this.wifipass = wifipass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(String maxprice) {
        this.maxprice = maxprice;
    }

    public String getMinprice() {
        return minprice;
    }

    public void setMinprice(String minprice) {
        this.minprice = minprice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
