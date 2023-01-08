package Question_5;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Priority_Queue {

    public static void main(String[] args) {
        PriorityQueue<Integer> que = new PriorityQueue<Integer>();




        Insert(que, 99);
        Insert(que, 3);
        Insert(que, 2);
        Insert(que, 5);
        Insert(que, 44);
        Insert(que, 0);

        System.out.println(Find2(que));


        Delete(que);
        Delete(que);
        Delete(que);
        System.out.println(que.poll());
        System.out.println(que.poll());
        System.out.println(que.poll());

        System.out.println("size"+que.size());


        System.out.println(IsEmpty(que));
        System.out.println("size"+que.size());
        System.out.println();

        Delete(que);
        System.out.println();
        System.out.println();
        System.out.println();










    }


    public static Integer Find2(PriorityQueue<Integer> queue){
      if(queue.size()<2){
          return null;
      }
      else {
            Integer small1 = queue.poll();
            Integer small2 = queue.peek();
            queue.offer(small1);
          return small2;
      }

    }
    public static void Delete(PriorityQueue<Integer> queue){
        if (!IsEmpty(queue)){
            queue.remove();
        }

    }
    public static void Insert(PriorityQueue<Integer> queue, Integer i){
        queue.offer(i);

    }
    public static boolean IsEmpty(PriorityQueue<Integer> queue) {
     Iterator itr = queue.iterator();
         return !itr.hasNext();




    }
    }




