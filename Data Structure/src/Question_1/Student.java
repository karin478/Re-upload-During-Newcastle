package Question_1;

public class Student {
    private String name;
    private String ID;
    private String programme_of_study;
    private String address;
    private String university_name;

    public Student(String name, String ID, String programme_of_study, String address, String university_name) {
        this.name = name;
        this.ID = ID;
        this.programme_of_study = programme_of_study;
        this.address = address;
        this.university_name = university_name;
    }
    public Student(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setProgramme_of_study(String programme_of_study) {
        this.programme_of_study = programme_of_study;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getProgramme_of_study() {
        return programme_of_study;
    }

    public String getAddress() {
        return address;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void insert(String name, String ID, String programme_of_study, String address, String university_name){
    setName(name);
    setID(ID);
    setProgramme_of_study(programme_of_study);
    setAddress(address);
    setUniversity_name(university_name);
    }
    public String print(){
        return "Name: " + getName() + " ID: " + getID() + " Programme of study: " + getProgramme_of_study()+" Address: "+getAddress()+" University name: "+getUniversity_name();
    }


}
