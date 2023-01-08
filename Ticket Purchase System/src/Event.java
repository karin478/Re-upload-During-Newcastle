import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**/
public class Event implements Comparable<Event>{
/*This is the event class. In this class we define event rules, formats and methods*/
    private String EventName;
    private int TicketNumber;
    public ArrayList<Event> eventArrayList;
    private int TicketSoldNumber;
    private int ClientNeed;
    private int ClientNeedReturn;

    /*Constructor*/
    public Event(String eventName, int ticketNumber, int ticketSoldNumber, int ClientNeed, int ClientNeedReturn) {
        EventName = eventName;
        TicketNumber = ticketNumber;
        TicketSoldNumber = ticketSoldNumber;
        this.ClientNeed = ClientNeed;
        this.ClientNeedReturn = ClientNeedReturn;
    }

    /*getter and setter*/
    public Event(ArrayList<Event> eventArrayList) {
        this.eventArrayList = eventArrayList;
    }
    public Event(){}

    public Event(int e) {
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public int getTicketNumber() {
        return TicketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        TicketNumber = ticketNumber;
    }

    public int getTicketSoldNumber() {
        return TicketSoldNumber;
    }

    public void setTicketSoldNumber(int ticketSoldNumber) {
        TicketSoldNumber = ticketSoldNumber;
    }

    public boolean ticketEnough()
    {
        return (TicketNumber >= ClientNeed);
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

    /*Defines the method of buying event tickets*/
    public void BuyTicketE(PrintWriter out,Client c) throws IOException {
        if (getTicketNumber()==0||!(ticketEnough())){    /*If the number of tickets is 0, call TicketSoldOut()*/
            TicketSoldOut(c,out);
        }
        if (ticketEnough()&&c.getClientTicketNumber()<3){    /*If the tickets are sufficient and the weight of the
                                                                tickets purchased by the guest is less than 3 types.*/
            setTicketNumber(getTicketNumber()-getClientNeed());
            setTicketSoldNumber(getTicketSoldNumber()+getClientNeed());
        }
        if (ticketEnough()&&c.getClientTicketNumber()==3){     /*If the tickets are sufficient, and the weight of the tickets purchased by the guest is 3 types, and the tickets purchased this time are the types that have already been purchased.*/
            if((c.map.containsKey(getEventName()))){
            setTicketNumber(getTicketNumber()-getClientNeed());
            setTicketSoldNumber(getTicketSoldNumber()+getClientNeed());
        }}

    }
    /*If the tickets are sufficient, and the weight of the tickets purchased by the guest is 3 types, and the tickets purchased this time are the types that have already been purchased.*/
    public void ReturnTicketE(Client c){
if (!(c.map.get(EventName)==null)){
        if (getClientNeedReturn()<=c.map.get(EventName)){
            setTicketSoldNumber(getTicketSoldNumber()-getClientNeedReturn());
            setTicketNumber(getTicketNumber()+getClientNeedReturn());
        }
}
    }

    /*If the tickets are sold out, send the information in the letter.txt file and print it to the console and records in clerk.txt.*/
    public void TicketSoldOut(Client c,PrintWriter out) throws IOException {
        PrintWriter out2 = null;
        try {
            out2 = new PrintWriter(new FileWriter(
                    "src/letters.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String letter = "Dear "+c.getFirstName()+" "+c.getSurName()+"\n"+"\nWe are sorry to inform you that the ticket of: "+getEventName()+" have been sold out or less than your requirement. Please check the information of event ticket\n"+"\nBest wish";
        System.out.println(letter);
        out.println(letter);
        out2.println(letter);

        out2.close();


    }
/*Rewrite the compareTo method, compare with the letters of eventname, and insert the return value -1 or 1 back to the SortedArraylist class*/
        @Override
        public int compareTo(Event e) {
            int result;
            if (this.getEventName() == null) {
                if (e.getEventName() == null) {
                    result = 0;
                }
                else {
                    result = -1;
                }
            }
            else {
                if (this.getEventName() == null) {
                    result = 1;
                }
                else {
                    result = this.getEventName().compareTo(e.getEventName());
                }
            }
            return result;
        }

/*Rewrite the output format of Event toString*/
    @Override
    public String toString(){

        return  "\n      Event name is:"+EventName+"\n      Ticket number is:      "+TicketNumber;
    }


}
