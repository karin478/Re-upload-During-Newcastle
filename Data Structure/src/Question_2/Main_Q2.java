package Question_2;

import java.util.ArrayList;
import java.util.List;

public class Main_Q2 {
    public static void main(String[] args) {
        Employees emp = new Employees();
        emp.insert("aa","bb","cc","dd");
        System.out.println("Employee test: "+emp.print());
        System.out.println("===================================================");

        Academic_Staff academic_staff = new Academic_Staff();
        academic_staff.insert("aa","bb","cc","dd","ee");
        System.out.println("Academic_staff test: "+ academic_staff.print());
        System.out.println("===================================================");

        Research_Staff research_staff = new Research_Staff();
        ArrayList<Research_Staff> researchList= new ArrayList<>();
        research_staff.insert("aa","bb","cc","dd","math",researchList);
        researchList.add(research_staff);
        System.out.println("Research_staff test: "+ research_staff.print());
        System.out.println("Search function test who study English:  "+ research_staff.search("English"));
        System.out.println("Search function test who study math: "+ research_staff.search("math"));
        System.out.println("===================================================");

        Student_Q2 stu = new Student_Q2();
        Student_Q2 stu2 = new Student_Q2();
        ArrayList<Student_Q2> studentList= new ArrayList<>();
        stu.insert("aa","bb","computer","dd","ee", "tt", studentList);
        stu2.insert("a","b","math","d","e", "y", studentList);
        studentList.add(stu);
        studentList.add(stu2);
        System.out.println("Stident_Q2 test: "+ stu.print());
        System.out.println("student study program search test: " + stu.search());
        System.out.println("===================================================");

        Identification_card idcard = new Identification_card();
        idcard.insert("aa", "bb", "cc");
        System.out.println("ID card test: " + idcard.print());
        System.out.println("===================================================");




    }


}
