package Question_6;

import java.util.Stack;

public class Stack_self_fun {
    public static void main(String[] args) throws Exception {

        Stack_self<Integer> s = new Stack_self(5);
        s.push(3);
        s.push(7);
        s.push(2);
        s.push(2);
        s.push(77);

        System.out.println("original stack：");
        s.printstack();
        System.out.println("-------------------------");
        System.out.println("after reverse stack：");
        s.reversestack();
        System.out.println("--------------------------");
        System.out.println("after delete stack：");
        s.removeelement(2);

        System.out.println("---------------------------");




    }
/*

    public static void printStack(Stack_self<Integer> stack){
        Stack_self<Integer> stackC = (Stack_self<Integer>)() stack.clone ();
        Stack_self<Integer> stackcc = stack.clone()
        while(!stackC.isEmpty()){
            System.out.println(stackC.pop());
        }
    }

    public static Stack<Integer> reverseStack(Stack_self<Integer> stack){
        Stack<Integer> stackC = (Stack<Integer>) stack.clone ();
        Stack<Integer> stack2 = new Stack<Integer>();

        while(!stackC.isEmpty()){
            stack2.push(stackC.pop());
        }
        return stack2;
    }

    public  static Stack<Integer> removeElement(Stack_self<Integer> stack, int val){
        Stack<Integer> stackC = (Stack<Integer>) stack.clone ();
        Stack<Integer> stack2 = new Stack<Integer>();

        while(!stackC.isEmpty()){
            if(!stackC.peek().equals(Integer.valueOf(val))){
                stack2.push(stackC.pop());
            }
            else {
                stackC.pop();
            }

        }
        return reverseStack(stack2);
    }



    */

}
