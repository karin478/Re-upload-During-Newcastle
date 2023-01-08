package Question_2;

import java.util.ArrayList;
import java.util.List;


public class Research_Staff extends Employees {
    private String research_domain;
    private ArrayList<Research_Staff> researcher_domain_list = new ArrayList<>();


    public Research_Staff(String name, String ID, String address, String university_name, String research_domain, ArrayList<Research_Staff> researcher_domain_list) {
        super(name, ID, address, university_name);
        this.research_domain = research_domain;
        this.researcher_domain_list = researcher_domain_list;
    }

    public Research_Staff() {
    }


    public String getResearch_domain() {
        return research_domain;
    }

    public void setResearch_domain(String research_domain) {
        this.research_domain = research_domain;
    }

    public ArrayList<Research_Staff> getResearcher_domain_list() {
        return researcher_domain_list;
    }

    public void setResearcher_domain_list(ArrayList<Research_Staff> researcher_domain_list) {
        this.researcher_domain_list = researcher_domain_list;
    }

    public String search(String domain){
    for(int i = 0; i<researcher_domain_list.size();i++){
        if(domain.equals(researcher_domain_list.get(i).getResearch_domain())){

                return researcher_domain_list.get(i).print();

        }
    }
    return "Not found";
}

    public String search1(String domain){
        for(int i = 0; i<researcher_domain_list.size();i++){
            if(domain.equals(researcher_domain_list.get(i).getResearch_domain())){
                    return researcher_domain_list.get(i).print();
            }
        }
        return "Not found" + researcher_domain_list.get(0).getResearch_domain();
    }


    public void insert(String name, String ID, String address, String university_name, String research_domain, ArrayList<Research_Staff> researcher_domain_list) {

        super.insert(name, ID, address, university_name);
        setResearch_domain(research_domain);
        setResearcher_domain_list(researcher_domain_list);
    }

    @Override
    public String print() {
        return super.print()+" research domain: "+ research_domain;
    }
}
