package Question_2;

import java.util.ArrayList;


public class Identification_card extends Student_Q2 {
    private String student_name;
    private String student_id;
    private String department_name;

    public Identification_card(String name, String ID, String address, String university_name, String research_domain, ArrayList<Research_Staff> researcher_domain_list, String programme_of_study, String department_name) {
        super(name, ID, address, university_name, research_domain, researcher_domain_list, programme_of_study);
        this.student_id = ID;
        this.student_name = name;
        this.department_name = department_name;
    }

    public Identification_card(String student_name, String student_id, String department_name) {
        this.student_name = student_name;
        this.student_id = student_id;
        this.department_name = department_name;
    }



    public Identification_card(){}




    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }


    public void insert(String name, String ID, String department_name) {
       setStudent_id(ID);
       setStudent_name(name);
       setDepartment_name(department_name);
    }

    @Override
    public String print() {
        return "  student id: "+ student_id +" student name: "+student_name+" department name: "+ department_name;
    }
}
