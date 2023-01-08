package Question_5;

import java.util.ArrayList;
import java.util.Comparator;

public class Queue_self {

    ArrayList<Integer> que = new ArrayList();



    public void push(int num){
        que.add(num);
        que.sort(Comparator.naturalOrder());
    }
    public void remove(){
        if (que.size()>0){
        que.remove(0);
    }
    }
    public Integer peek(){
        return que.get(0);
    }
    public Integer poll(){
        Integer res = que.get(0);
        que.remove(0);
        return res;
    }
    public boolean IsEmpty(){
        if(que.size() == 0){
            return true;
        }
        else {
            return false;
        }

    }
    public int size(){
        return que.size();

    }




}
