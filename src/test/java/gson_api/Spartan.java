package gson_api;

    /*
        {
        "id": 15,
        "name": "Meta",
        "gender": "Female",
        "phone": 1938695106
        }
     */

public class Spartan {

    private int id;
    private String name;
    private String gender;
    private Long phone;

    public Spartan() {
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }

    public Spartan(int id, String name, String gender, Long phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getJsonAsStringForPost(){
        String json="{" +
                        "\"name\":" + "\"" + this.name + "\"," +
                        "\"gender\":" + "\"" + this.gender + "\"," +
                        "\"phone\":" + this.phone+
                    "}";

        return json;
    }



}
