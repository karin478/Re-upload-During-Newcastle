package Question_2;

import java.util.ArrayList;
import java.util.List;

public class Academic_Staff extends Employees {
    private String position_title;
    List<Academic_Staff> academic_staffList = new ArrayList<>();

    public Academic_Staff(String name, String ID, String address, String university_name, String position_title) {
        super(name, ID, address, university_name);
        this.position_title = position_title;
    }
    public Academic_Staff() {

    }

    public String getPosition_title() {
        return position_title;
    }

    public void setPosition_title(String position_title) {
        this.position_title = position_title;
    }

    public List<Academic_Staff> getAcademic_staffList() {
        return academic_staffList;
    }

    public void setAcademic_staffList(List<Academic_Staff> academic_staffList) {
        this.academic_staffList = academic_staffList;
    }

    public void print_info(String Id){
    for(int i = 0; i<academic_staffList.size();i++){
        if(academic_staffList.get(i).getID().equals(Id)){
            System.out.println(academic_staffList.get(i).print());
        }
    }
    }


    public void insert(String name, String ID, String address, String university_name, String position_title) {
        super.insert(name, ID, address, university_name);
        setPosition_title(position_title);
    }

    @Override
    public String print() {
        return super.print()+" position_title: "+position_title;
    }
}
