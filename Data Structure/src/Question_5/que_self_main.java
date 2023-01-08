package Question_5;



public class que_self_main {

    public static void main(String[] args) {

        Queue_self queue_self = new Queue_self();

        Insert(queue_self, 99);
        Insert(queue_self, 3);
        Insert(queue_self, 2);
        Insert(queue_self, 5);
        Insert(queue_self, 44);
        Insert(queue_self, 0);





        System.out.println(queue_self.peek());

        System.out.println(Find2(queue_self));

        System.out.println("size"+queue_self.size());


        System.out.println(IsEmpty(queue_self));
        System.out.println("size"+queue_self.size());
        System.out.println();

        Delete(queue_self);
        System.out.println();
        System.out.println();
        System.out.println();






    }



    public static Integer Find2(Queue_self queue_self){
        if(queue_self.size()<2){
            return null;
        }
        else {
            Integer small1 = queue_self.poll();
            Integer small2 = queue_self.peek();
            queue_self.push(small1);
            return small2;
        }

    }
    public static void Delete(Queue_self queue_self){
        if (!IsEmpty(queue_self)){
            queue_self.remove();
        }

    }
    public static void Insert(Queue_self queue_self, Integer i){
        queue_self.push(i);

    }
    public static boolean IsEmpty(Queue_self queue_self) {

        return  queue_self.IsEmpty();




    }















}
