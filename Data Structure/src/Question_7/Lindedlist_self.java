package Question_7;

public class Lindedlist_self {
    Node head;
    int size;
    Node tail;

    public Lindedlist_self(){
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty(){
        return head == null;
    }
    public void addNodeAnyPosition(int position, int data){
    if (position>size){
        return;
    }
    Node newNode = new Node(data);
    if(position == 0){
        newNode.next = head;
        head = newNode;
        if(tail == null){
            tail = newNode;
        }
        size++;
    }
    else if (position == size){
        this.addNodeLast(data);
    }
    else {
        Node pre = head;
        for (int i=0;i<position;i++){
            pre = pre.next;
        }
        Node nextNode = pre.next;
        newNode.next = nextNode;
        pre.next = newNode;
        size++;
    }

    }

    public void addNodeLast(int data){
        Node newNode = new Node(data);
        if (tail == null){
            tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    public void delete(int position){
        if (position<1 || position>size){
            return;
        }
        if (position == 1){
            head = head.next;
        }
        int i=1;
        Node pre = head;
        Node cur = pre.next;
        while(cur != null){
            if (i == position){
                pre.next = cur.next;
            }
            pre = cur;
            cur = cur.next;
            i++;
        }
    }
    public void printlist(){
        Node tep = head;
        while (tep != null){
            System.out.println(tep.data);
            tep = tep.next;
        }
    }

    public Node SearchNode(int position){
        Node tep = head;
        if(position<0 || position>size){
            return null;
        }
        int i=1;
        while (i<=position){
            tep = tep.next;
            i++;
        }

        return tep;
    }


    public void printreverse(){
        Lindedlist_self reverselinkedlist = new Lindedlist_self();
        for(int i=0;i<size;i++){
            reverselinkedlist.addNodeAnyPosition(0, SearchNode(i).data);

        }
        reverselinkedlist.printlist();
    }



}
