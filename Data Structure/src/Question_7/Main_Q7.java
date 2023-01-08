package Question_7;

public class Main_Q7 {
    public static void main(String[] args) {

        Lindedlist_self linkedlist = new Lindedlist_self();
        linkedlist.addNodeAnyPosition(0,1);
        linkedlist.addNodeLast(2);
        linkedlist.addNodeLast(3);
        linkedlist.addNodeLast(4);
        linkedlist.addNodeLast(5);
        System.out.println("original linkedlist: ");
        linkedlist.printlist();
        System.out.println("==========================================");
        System.out.printf("after reverse:   \n");
        linkedlist.printreverse();





    }

}
