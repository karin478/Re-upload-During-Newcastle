package Question_7;

import java.util.LinkedList;
import java.util.Stack;

public class Reverse_LinkedList {
    public static void main(String[] args) {
    LinkedList<Integer> linkedList = new LinkedList<Integer>();
    linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        linkedList.add(40);
        linkedList.add(50);
    System.out.println(linkedList);
   reverseL(linkedList);
        System.out.println();
        System.out.println();
        System.out.println();

    }


    public static void reverseL(LinkedList<Integer> linkedList){
        Stack<Integer> stack = new Stack<Integer>();
        int j = 0;
        while (j<linkedList.size()){
            stack.push (linkedList.get(j));
            j++;
        }
        int i = 0;
        while (!stack.isEmpty()) {
            System.out.print(stack.pop()+"  ");
            i++;
        }
    }


}
