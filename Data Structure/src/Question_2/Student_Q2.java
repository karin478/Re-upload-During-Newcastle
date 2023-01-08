package Question_2;

import java.util.ArrayList;
import java.util.List;

public class Student_Q2 extends Research_Staff {
    private String programme_of_study;
    private ArrayList<Student_Q2> student_list = new ArrayList<>();


    public Student_Q2(String name, String ID, String address, String university_name, String research_domain, ArrayList<Research_Staff> researcher_domain_list, String programme_of_study) {
        super(name, ID, address, university_name, research_domain, researcher_domain_list);
        this.programme_of_study = programme_of_study;
    }


    public Student_Q2() {

    }


    public String getProgramme_of_study() {
        return programme_of_study;
    }

    public void setProgramme_of_study(String programme_of_study) {
        this.programme_of_study = programme_of_study;
    }

    public ArrayList<Student_Q2> getStudent_list() {
        return student_list;
    }

    public void setStudent_list(ArrayList<Student_Q2> student_list) {
        this.student_list = student_list;
    }

    public String search(){
        boolean found = false;
    ArrayList<String> All_Id = new ArrayList<>();
        for(int i = 0; i < student_list.size();i++) {
            if (student_list.get(i).getProgramme_of_study().equals("computer")) {
               All_Id.add(student_list.get(i).getID());
               found = true;
            }

        }
        if(found){
            return All_Id.toString();
        }else {
            return "not found";
        }

    }
    public void insert(String name, String ID, String programme_of_study, String address, String university_name, String research_domain, ArrayList<Student_Q2> student_list){
        setName(name);
        setID(ID);
        setProgramme_of_study(programme_of_study);
        setAddress(address);
        setUniversity_name(university_name);
        setResearch_domain(research_domain);
        setStudent_list(student_list);
    }

    @Override
    public String print() {
        return super.print() + " Programme of study: " + getProgramme_of_study();
    }





}
