
import java.io.*;
import java.util.*;


public class MainProgram {
    /*the static filed we need to use*/
    static Scanner k = new Scanner(System.in);
    static ArrayList<Event> events = new ArrayList<>();
    static ArrayList<Client> clients = new ArrayList<>();
    static SortedArrayList<Event> EventSort = new SortedArrayList<>();
    static SortedArrayList<Client> ClientSort = new SortedArrayList<>();

    /*The main method controls the console and needs to be able to throw exceptions because it needs to read files.*/
    public static void main(String[] args) throws IOException {
        /*Define and create our printing method. Through printwriter, we can output all the input and output of all our consoles to clerk.txt*/
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(
                    "src/clerk.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReadFile();
        printMenu(out);
        char ch = k.next().charAt(0);
        k.nextLine();
/*The loop of the menu, use while to loop continuously when "f" is not input*/
        while (ch != 'f') {
            out.println(ch);
            switch (ch) {
                /*When inputting "e", we print all activity information that has been sorted by SortedArraylist.*/
                case 'e' -> {
                    for (Event event : EventSort) {
                        out.println(event);
                        System.out.println(event);
                    }
                }
                /*When entering "c", we print all client information that has been sorted by SortedArraylist.*/
                case 'c' -> {
                    for (Client client : ClientSort) {
                        out.println(client);
                        System.out.println(client);
                    }
                }
                /*When entering b, check and book the ticket*/
                case 'b' -> {
                    out.println("Please enter the name of the client");
                    System.out.println("Please enter the name of the client");
                    String c = k.nextLine();
                    out.println(c);
                    /*if enter the wrong Client information*/
                    if ((FindObjC(c) == null)) {
                        out.println("You enter the wrong Client information, back to the menu");
                        System.out.println("You enter the wrong Client information, back to the menu");
                        break;
                    }
                    out.println("Please enter the event name that client want to buy the ticket");
                    System.out.println("Please enter the event name that client want to buy the ticket");
                    String e = k.nextLine();
                    out.println(e);
                    /*if enter the wrong Event information*/
                    if ((FindObjE(e) == null)) {
                        out.println("You enter the wrong Event information, back to the menu");
                        System.out.println("You enter the wrong Event information, back to the menu");
                    }
                    /*if the information is all correct*/
                    else {
                        /*If the number of tickets is greater than 0,Purchasing objects by calling the objects e: event and c: client of the array,if the ticket is sold out we can run "EventBuyer" which including the letter method send letter to the client*/
                        System.out.println("Please enter how much number ticket you want to buy");
                        out.println("Please enter how much number ticket you want to buy");
                        String G = k.nextLine();
                        out.println(G);
                        if (IsNum(G)){
                        int g = Integer.parseInt(G);
                        if (g>0){
                        Objects.requireNonNull(FindObjC(c)).setClientNeed(g);
                        Objects.requireNonNull(FindObjE(e)).setClientNeed(g);
                        if (!(FindObjE(e) == null)) {
                            (Objects.requireNonNull(FindObjC(c))).BuyTicketC((Objects.requireNonNull(FindObjE(e))), out);
                                (Objects.requireNonNull(FindObjE(e))).BuyTicketE(out,FindObjC(c));
                        }
                        }
                    }
                        /*if the number entered is not number of is minus number, we can reject number and go back to menu*/
                        else {
                            System.out.println("Your number beyond requirement");
                             out.println("Your number beyond requirement");
                        }
                    }
                }
                /*Refund option. Most of the logic and input detection are exactly the same as the ticket purchase options above*/
                case 'r' -> {
                    out.println("Please enter the name of the client");
                    System.out.println("Please enter the name of the client");
                    String ct = k.nextLine();
                    out.println(ct);
                    if ((FindObjC(ct) == null)) {
                        out.println("You enter the wrong Client information, please enter again");
                        System.out.println("You enter the wrong Client information, please enter again");
                    }
                    else {
                    out.println("Please enter the event name that client want to return the ticket");
                    System.out.println("Please enter the event name that client want to return the ticket");
                    String et = k.nextLine();
                    out.println(et);
                    if ((FindObjE(et) == null)) {
                        out.println("You enter the wrong Event information, please enter again");
                        System.out.println("You enter the wrong Event information, please enter again");
                    }
                    else {
                        System.out.println("Please enter the number of ticket you want to return");
                        out.println("Please enter the number of ticket you want to return");
                        String H2 = k.nextLine();
                        out.println(H2);
                        if (IsNum(H2)){
                            int h2 = Integer.parseInt(H2);
                        if(h2>0){
                        Objects.requireNonNull(FindObjE(et)).setClientNeedReturn(h2);
                        Objects.requireNonNull(FindObjC(ct)).setClientNeedReturn(h2);
                            if (!(FindObjE(et) == null)) {
                                (Objects.requireNonNull(FindObjE(et))).ReturnTicketE((Objects.requireNonNull(FindObjC(ct))));
                                (Objects.requireNonNull(FindObjC(ct))).ReturnTicketC((Objects.requireNonNull(FindObjE(et))), out);
                        }
                        }
                        }
                        else {
                            System.out.println("Your number beyond requirement");
                            out.println("Your number beyond requirement");
                        }

                        }
                    }
                }
                /*if you enter the wrong information in the main menu there is response*/
                default -> {
                    System.out.println("You enter the wrong letter please try again");
                    out.println("You enter the wrong letter please try again");
                }
            }
            printMenu(out);
            ch = k.next().charAt(0);
            k.nextLine();
        }
/*In order to make the content in the clerk file exactly the same as the content displayed on the console. When the user enters f to exit the console, the system will automatically shut down. Use pwriter to enter f to keep the console consistent with clerk, and let clerk readers know that the program has exited*/
        out.println("f");
        out.close();
    }

/*The method of printing the menu, and output to clerk at the same time*/
    private static void printMenu(PrintWriter out) {
        out.println("------------------------------");
        out.println("MENU");
        out.println("f - to finish running the program");
        out.println("e - to display on the screen information about all the events");
        out.println("c - to display on the screen information about all the clients");
        out.println("b - to update the stored data when tickets are bought by one of the registered clients");
        out.println("r - to update the stored data when a registered client cancels/returns tickets");
        out.println("------------------------------");
        out.println("Type a letter and press Enter");

        System.out.println("------------------------------");
        System.out.println("MENU");
        System.out.println("f - to finish running the program");
        System.out.println("e - to display on the screen information about all the events");
        System.out.println("c - to display on the screen information about all the clients");
        System.out.println("b - to update the stored data when tickets are bought by one of the registered clients");
        System.out.println("r - to update the stored data when a registered client cancels/returns tickets");
        System.out.println("------------------------------");
        System.out.println("Type a letter and press Enter");
    }

    /*Read the input file and save the information in the file in Arraylist*/
    /*we will explain each variable in the below*/
    private static void ReadFile() throws IOException {
        int eventnum = 0;
        int clientnum = 0;
        int clientlinenum = 0;
        int i = 1;

/*Read files through bufferedreader and scanner*/
        File f = new File((Event.class.getResource(
                "input.txt")).getFile());
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line;
        Scanner scanner;
        List<Event> listE = new ArrayList<>();
        List<Client> listC = new ArrayList<>();

/*Read each line until there is no content afterwards*/
        while ((line = reader.readLine()) != null) {
            scanner = new Scanner(line);
            String text1 = scanner.nextLine();
            /*eventnum indicates how many kinds of events are there. clientlinenum indicates the location of how many client data is displayed in the txt text. clientnum indicates how many clients have been registered*/
            if (i == 1) {
                eventnum = Integer.parseInt(text1);
            }
            clientlinenum = eventnum * 2 + 2;
            if (i == clientlinenum) {
                clientnum = Integer.parseInt(text1);
            }
            i++;
        }
        /*Create an object array based on the number of events and the number of clients read in the file.*/
        for (int e = 0; e < eventnum; e++) {
            Event eve = new Event(e);
            listE.add(eve);
        }
        for (int c = 0; c < clientnum; c++) {
            Client Cli = new Client(c);
            listC.add(Cli);
        }

/*The bufferreader in java will be unstable when it is reused. In order to ensure that the program can run on any computer, a new bufferreader will be restarted every time a file is read.*/
        BufferedReader reader2 = new BufferedReader(new FileReader(f));
        int i1 = 1;
        int g = 0;
        String line2;
        /*According to the number of clients and events, read the event information in the file and put it into the event array full of event objects*/
        while ((line2 = reader2.readLine()) != null&&i1 < clientlinenum) {
            Scanner scanner2 = new Scanner(line2);
            String text2 = scanner2.nextLine();
                if (i1 % 2 == 0) {
                    listE.get(g).setEventName(text2);
                    g++;
                }
                if (i1 % 2 == 1&&g!=0) {
                    listE.get(g-1).setTicketNumber(Integer.parseInt(text2));

                }

                i1++;
            }

        /*According to the number of clients and events, read the client information in the file and put it into the client array filled with client objects*/
        BufferedReader reader3 = new BufferedReader(new FileReader(f));
            int g2 = 0;
            int i2 = 1;
            while ((line = reader3.readLine()) != null) {
                scanner = new Scanner(line);
                String text3 = scanner.nextLine();
                if (i2>clientlinenum) {
                    String[] nameSplit = text3.split(" ");
                    listC.get(g2).setFirstName(nameSplit[0]);
                    listC.get(g2).setSurName(nameSplit[1]);
                    g2++;
                }
                i2++;

            }

/*Put the objects in the event and client arrays that have obtained data into the events and client arrays, and execute the insert method on the events and client arrays. The final fully sorted list is EventSort and ClientSort*/
        for (int e = 0; e < eventnum; e++) {
           events.add(listE.get(e));
            EventSort.insert(events.get(e));
        }

        for (int c = 0; c < clientnum; c++) {
            clients.add(listC.get(c));
            ClientSort.insert(clients.get(c));
        }


        }

/*This method can select the event object from the arraylist and execute the method with this object*/
    public static Event FindObjE(String s) {

        for (int i = 0; i <= 5; i++) {
            if ((events.get(i).getEventName()).contains(s)) {
                return events.get(i);
            }


        }
             return null;
    }
    /*This method can select the client object from the arraylist and execute the method with this object*/
    public static Client FindObjC(String s) {
        for (int i = 0; i <= 2; i++) {
            if ((clients.get(i).getFirstName() + " " + clients.get(i).getSurName()).equals(s)) {
                return clients.get(i);
            }

        }
        return null;
    }

    /*we can not sure client will enter the number or some String. we can use this method to decide if we should get it*/
    public static boolean IsNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }



}






