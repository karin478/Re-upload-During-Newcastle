package Question_6;

import Question_5.Queue_self;

import java.util.Stack;

public class Stack_fun {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(3);
        s.push(7);
        s.push(2);
        s.push(1);
        s.push(77);

        System.out.println("original stack：");
        printStack(s);
        System.out.println("-------------------------");
        System.out.println("after reverse stack：");
        printStack(reverseStack(s));
        System.out.println("--------------------------");
        System.out.println("after delete stack：");

        printStack(removeElement(s,2));

        System.out.println("---------------------------");





    }

    public static void printStack(Stack<Integer> stack){
        Stack<Integer> stackC = (Stack<Integer>) stack.clone ();
       while(!stackC.isEmpty()){
           System.out.println(stackC.pop());
       }
    }

    public static Stack<Integer> reverseStack(Stack<Integer> stack){
        Stack<Integer> stackC = (Stack<Integer>) stack.clone ();
        Stack<Integer> stack2 = new Stack<Integer>();

        while(!stackC.isEmpty()){
            stack2.push(stackC.pop());
        }
        return stack2;
    }

    public  static Stack<Integer> removeElement(Stack<Integer> stack, int val){
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


    }



