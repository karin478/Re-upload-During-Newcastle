import java.io.PrintWriter;
import java.util.*;

/*The purpose of the Client class is to define the information, methods and data of the Client.*/
public class Client implements Comparable<Client> {
    private int ClientTicketNumber=0;
    private String firstName;
    private String surName;
    private int ClientNeed;
    private int ClientNeedReturn;

    public HashMap<String,Integer> map = new HashMap();



/*Constructor and getter setter*/
    public Client(String firstName, String surName, int clientTicketNumber, int ClientNeed, int ClientNeedReturn) {
        this.firstName = firstName;
        this.surName = surName;
        this.ClientTicketNumber = clientTicketNumber;
        this.ClientNeed = ClientNeed;
        this.ClientNeedReturn = ClientNeed;
    }

    public Client(int c) {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getClientTicketNumber() {
        return ClientTicketNumber;
    }

    public void setClientTicketNumber(int clientTicketNumber) {
        ClientTicketNumber = clientTicketNumber;
    }

    public int getClientNeed() {
        return ClientNeed;
    }

    public void setClientNeed(int clientNeed) {
        ClientNeed = clientNeed;
    }

    public int getClientNeedReturn() {
        return ClientNeedReturn;
    }

    public void setClientNeedReturn(int clientNeedReturn) {
        ClientNeedReturn = clientNeedReturn;
    }

    /*This method is that after the user refunds the ticket, the event name still exists, but the number of tickets is 0, which is not beautiful. With this method, the redundant information in the client information can be removed after each refund. Then when toString detects that the ticket information of the Client is empty, it can add String information that you have not booked any tickets.*/
public void ClintNumberCheck(){
    Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
    while(it.hasNext()){
        Map.Entry<String,Integer> entry = it.next();
        if (entry.getValue() == 0){
            it.remove();
        }
    }
}

/*This method is used to purchase tickets on the Client side.*/
public void BuyTicketC(Event e,PrintWriter out) {
/*When the newly purchased ticket type already exists, directly increase the number of tickets*/
    if ((map.containsKey(e.getEventName()))&&e.ticketEnough()) {
        map.put(e.getEventName(), map.get(e.getEventName()) + getClientNeed());

    } else {
        /*When the ticket is a new ticket*/
        if (getClientTicketNumber() <= 3) {
            /*When there are already 3 types of tickets, the purchase is refused at this time and the information that you cannot purchase is displayed.*/
            if (getClientTicketNumber() == 3) {
                System.out.println("You can't not buy more than 3 event ticket");
                out.println("You can't not buy more than 3 event ticket");
            }
            /*Now there are less than 3 types of tickets, so add this new ticket*/
            if (getClientTicketNumber() < 3) {
                if (e.ticketEnough()) {
                    map.put(e.getEventName(), e.getClientNeed());
                    setClientTicketNumber(getClientTicketNumber() + 1);
                }
            }
        }

    }
}


/*This method defines how to proceed when the user refunds the ticket*/
public void ReturnTicketC(Event e, PrintWriter out){

/*If you want to cancel the ticket, the guest does not hold at least one ticket. Then it shows that the refund failed*/
    if(!(map.containsKey(e.getEventName()))||map.get(e.getEventName())==0){
        System.out.println("Return ticket fails, please check your ticket information");
        out.println("Return ticket fails, please check your ticket information");
    }

    /*if client have tickets and 1:require ticket beyond client have. 2:not beyond and client can get new ticket number by previous one - ClientNeedReturn. if ticket become to one, we can use Check()method to clean the toString */
    else {
            if (getClientNeedReturn()>map.get(e.getEventName())) {
                System.out.println("Return ticket fails, you don't have enough ticket");
                out.println("Return ticket fails, you don't have enough ticket");
            }
            else {
                map.put(e.getEventName(), map.get(e.getEventName())-ClientNeedReturn);
                if (map.get(e.getEventName())==0){
                setClientTicketNumber(getClientTicketNumber() - 1);
                ClintNumberCheck();
            }
            }
        }
    }


/*Rewrite the tostring format to display the information we need in the main call*/
    @Override
    public String toString(){
        /*if there is no ticket in client order*/
     if (map.isEmpty()){
         return "Dear "+firstName + " " + surName +": You haven't order any ticket\n";
     }
     /*if there are the tickets client order*/
     else {
         return "Dear "+firstName + " " + surName +" your order information: "+ List.of(map) +"\n";
     }

    }
/*Override the compareto method to take the value of the client's surname. Return 1 or -1 through surname comparison to sortedarraylist for insertion*/
    @Override
    public int compareTo(Client c) {
        int result;
        if (this.getSurName() == null) {
            if (c.getSurName() == null) {
                result = 0;
            }
            else {
                result = -1;
            }
        }
        else {
            if (this.getSurName() == null) {
                result = 1;
            }
            else {
                result = this.getSurName().compareTo(c.getSurName());
            }
        }
        return result;
    }
}
