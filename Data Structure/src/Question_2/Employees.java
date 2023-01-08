package Question_2;

public class Employees {
    private String Name;
    private String ID;
    private String address;
    private String university_name;

    public Employees(String name, String ID, String address, String university_name) {
        this.Name = name;
        this.ID = ID;
        this.address = address;
        this.university_name = university_name;
    }
    public Employees() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }


    public void insert(String name, String ID, String address, String university_name){
        setAddress(address);
        setID(ID);
        setName(name);
        setUniversity_name(university_name);

    }
    public String print(){
        return " Name: " + getName() + " ID: " + getID()  +" Address: "+getAddress()+" University name: "+getUniversity_name();
    }

}
