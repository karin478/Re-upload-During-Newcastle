package uk.ncl.CSC8016.jackbergus.coursework;

import uk.ncl.CSC8016.jackbergus.coursework.*;
import uk.ncl.CSC8016.jackbergus.coursework.utils.AtomicBigInteger;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


//Extends the bankface class
public class BankOperator extends BankFacade {

    //field defines hashmaps, locks, and other static variables. and used volatile for multithreading optimization
    HashMap<String, Double> hashMap;
    AtomicBigInteger abi;
    private ReentrantLock mutex;
    volatile static double temptotal = 0;
    volatile double totalLocalOperations = 0;
    static Scanner k = new Scanner(System.in);
    static volatile HashMap<String, Double> hashmap2 = new HashMap<String, Double>(2, 20);
    List<Operation> journal = new ArrayList<>();
    volatile List<Operation> journalIgnore = new ArrayList<>();
    volatile int JournalTime = 0;
    volatile List<String> journalStringBeforeCommitSuccess = new ArrayList<>();
    volatile List<String> journalStringBeforeCommitFail = new ArrayList<>();
    volatile List<String> journalStringSuccess = new ArrayList<>();
    volatile List<String> journalStringFail = new ArrayList<>();
    volatile List<String> journalStringAbort = new ArrayList<>();
    volatile static String userID;
    volatile static List<Thread> t;
    volatile static boolean IsThread2 = false;



//Constructor
    public BankOperator(HashMap<String, Double> userIdToTotalInitialAmount) {
        super(userIdToTotalInitialAmount);
        if ((userIdToTotalInitialAmount == null)) throw new RuntimeException();
        hashMap = new HashMap<>(userIdToTotalInitialAmount);
        abi = new AtomicBigInteger(BigInteger.ZERO);
        mutex = new ReentrantLock(true);
    }


    @Override
    public String StudentID() {
        return "200752417";
    }

    //Extend Transaction Commands by overriding
    @Override
    public Optional<TransactionCommands> openTransaction(String userId) {
        if(hashMap.containsKey(userId)) {
            return Optional.of(new TransactionCommands() {
                boolean isProcessDone, isProcessAborted, isProcessCommitted;





                BigInteger currentTransactionId;
                {

                    mutex.lock();
                    isProcessDone = isProcessAborted = isProcessCommitted = false;
                    currentTransactionId = abi.incrementAndGet();

                }
                @Override
                public BigInteger getTransactionId() {
                    return currentTransactionId;
                }

                //Find account balance through hashmap
                @Override
                public double getTentativeTotalAmount() {
                    mutex.lock();
                    if (!isProcessDone) {
                        mutex.unlock();
                        return hashMap.get(userId);
                    }
                    else{
                        mutex.unlock();
                        return -1;
                    }


                }
                //Successfully withdraw if there is enough money to withdraw, and send an error message if not enough
                @Override
                public boolean withdrawMoney(double amount) {
                    mutex.lock();
                    JournalTime++;
                    if ((amount < 0) || (isProcessDone)) {
                        mutex.unlock();
                        return false;
                    }
                    else {
                        double val = hashMap.get(userId)+temptotal;
                        if (val >= amount) {
                            journal.add(Operation.Withdraw(amount,JournalTime, userId));
                            journalStringBeforeCommitSuccess.add(Operation.Withdraw(amount,JournalTime, userId).toString());
  //                          journalStringSuccess.add(Operation.Withdraw(amount,JournalTime, userId).toString());

                            temptotal -= amount;
                            totalLocalOperations+=1;
                            System.out.println("Success Withdrew: " + amount);

                            mutex.unlock();
                            return true;
                        } else

                            journalIgnore.add(Operation.Withdraw(amount,JournalTime, userId));
                        journalStringBeforeCommitFail.add(Operation.Withdraw(amount,JournalTime, userId).toString());
     //                   journalStringFailOrAbort.add(Operation.Withdraw(amount,JournalTime, userId).toString());
                        totalLocalOperations+=1;
                        mutex.unlock();
                        return false;
                    }
                }
                //Deposit money into the selected account. Add a temporary balance before committing
                @Override
                public boolean payMoneyToAccount(double amount) {
                    mutex.lock();
                    JournalTime++;
                    if ((amount < 0) || (isProcessDone)) return false;
                    else {



                        temptotal += amount;
                        totalLocalOperations+=1;
                        System.out.println("Success Pay: " + amount);

                        System.out.println(JournalTime);


                        try {
                            journal.add(Operation.Pay(amount, JournalTime, userId));
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }



                        try {
                            journalStringBeforeCommitSuccess.add(Operation.Pay(amount, JournalTime, userId).toString());
 //                           journalStringSuccess.add(Operation.Pay(amount, JournalTime, userId).toString());
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }



                        mutex.unlock();
                        return true;
                    }
                }
                //Abandon the operation, all operations before the last commit will be cancelled
                @Override
                public synchronized void abort() {
                    mutex.lock();
                    if (!isProcessDone || totalLocalOperations!=0) {
                        isProcessDone = isProcessAborted = true;
                        isProcessCommitted = false;
                        temptotal = 0;
                        totalLocalOperations=0;
                        journalStringAbort.addAll(journalStringBeforeCommitSuccess);
                        journalStringBeforeCommitSuccess.clear();
                        journalStringAbort.addAll(journalStringBeforeCommitFail);
                        journalStringBeforeCommitFail.clear();


                        mutex.unlock();
                    }
                }
                //Submit all operations, cancel this command if there are no operations
                @Override
                public synchronized CommitResult commit() {
                    mutex.lock();
                    isProcessDone = true;
                    if (totalLocalOperations == 0){
                        System.out.println("you can not commit because you have to take at least one transaction before you commit");
                        return null;
                    }
                    if (totalLocalOperations!=0) {
                        isProcessAborted = false;
                        isProcessCommitted = true;
                        totalLocalOperations = temptotal;
                        hashMap.computeIfPresent(userId, (s, aDouble) -> aDouble += totalLocalOperations);
                        temptotal = 0;
                        totalLocalOperations=0;


                        journalStringSuccess.addAll(journalStringBeforeCommitSuccess);
                        journalStringFail.addAll(journalStringBeforeCommitFail);

                        journalStringBeforeCommitSuccess.clear();
                        journalStringBeforeCommitFail.clear();

                        System.out.println("Success action: ");
                        for (String stringSuccess : journalStringSuccess) {
                            System.out.println(stringSuccess.toString());

                        }
                        System.out.println();
                        System.out.println("Fail action: ");
                        for (String s : journalStringFail) {
                            System.out.println(s.toString());
                        }
                        System.out.println();
                        System.out.println("Aborted action: ");
                        for (String s : journalStringAbort) {
                            System.out.println(s.toString());
                        }

                        mutex.unlock();
                        return new CommitResult(journal, journalIgnore, hashMap.get(userId));
                    } else {
                        return null;
                    }
                }

            });
        } else
            return Optional.empty();
    }

    //The main interface of the bank. By entering different letters, users can choose the service they need.
    public static void BankMenu(){

        BankOperator wncq = new BankOperator(hashmap2);
        printMenu();
        char ch = k.next().charAt(0);

        k.nextLine();





        while (ch != 'f') {

            switch (ch) {

                case 'a' -> {

                    System.out.println("===========================================");
                    System.out.println("enter your userid to access your bank account(account1 / account2)");

                    userID = k.next();
                    k.nextLine();
                    System.out.println(userID);





                    System.out.println("Account balance： " + wncq.openTransaction(userID).get().getTentativeTotalAmount()  );


                }

                case 'b' -> {
                    System.out.println("account got the money, please commit or abort your action");

                    wncq.openTransaction(userID).get().payMoneyToAccount(20);
                    System.out.println("if you success commit, total change account will be: " + temptotal );
                    double tempT = temptotal + wncq.openTransaction(userID).get().getTentativeTotalAmount();
                    System.out.println("if you success commit, total account balance will be: " + tempT );
                }


                case 'c' -> {
                    System.out.println("account withdrew the money, please commit or abort your action");
                    boolean enough = wncq.openTransaction(userID).get().withdrawMoney(30);
                    if (enough){
                        System.out.println("withdrew successful");
                    }else {
                        System.out.println("balance not enough");
                    }
                    System.out.println("if you success commit, total change account will be: " + temptotal );
                    double tempT = temptotal + wncq.openTransaction(userID).get().getTentativeTotalAmount();
                    System.out.println("if you success commit, total account balance will be: " + tempT );
                }

                case 'd' -> {

                    System.out.println("have aborted");
                    wncq.openTransaction(userID).get().abort();


                }
                case 'e' -> {
                    System.out.println("have committed");
                    wncq.openTransaction(userID).get().commit();
                }
                case 'g' -> {

                    if (hashmap2.containsKey(userID)){
                        System.out.println("This is your balance of your account");
                        System.out.println(wncq.openTransaction(userID).get().getTentativeTotalAmount());


                    }else {
                        System.out.println("you should log in correct account and check again");
                    }
                }

                case 'h' -> {

                    System.out.println("account withdrew the money, please commit or abort your action");
                    boolean enough = wncq.openTransaction(userID).get().withdrawMoney(0);
                    if (enough){
                        System.out.println("withdrew successful");
                    }else {
                        System.out.println("balance not enough");
                    }
                    System.out.println("if you success commit, total change account will be: " + temptotal );
                    double tempT = temptotal + wncq.openTransaction(userID).get().getTentativeTotalAmount();
                    System.out.println("if you success commit, total account balance will be: " + tempT );


                }
                case 'i' -> {
                    System.out.println("change the thread");



                        if (IsThread2){
                        wncq.ChangetoThread2(t.get(0));
                    }else {
                            wncq.ChangetoThread1(t.get(1));
                        }
                    if (!IsThread2){
                        System.out.println("Thread number: 1" );
                    }if (IsThread2){
                        System.out.println("Thread number: 2" );
                    }
                    printMenu();
                }



                case 'j' -> {
                    t.add(generateThread(2, hashmap2));
                    t.get(1).start();
                    System.out.println("new thread 2");
                    if (IsThread2==false){
                        IsThread2 = true;
                    }else {
                        IsThread2 = false;
                    }


                    synchronized (t.get(0)) {

                            try {
                                t.get(0).wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();


                        }
                    }



                }
                default -> {
                    System.out.println("You enter the wrong letter please try again");

                }
            }

            ch = k.next().charAt(0);
            k.nextLine();
        }


    }


    public  synchronized void ChangetoThread1(Thread threadOpen) {
        while (IsThread2){
            try {
                threadOpen.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        if (IsThread2==false){
            IsThread2 = true;
        }else {
            IsThread2 = false;
        }
    }
    public  synchronized void ChangetoThread2(Thread threadOpen) {
        while (!IsThread2){
            try {
                threadOpen.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        if (IsThread2==false){
            IsThread2 = true;
        }else {
            IsThread2 = false;
        }
    }


    public static void printMenu(){
        System.out.println("===========================================");
        if (!IsThread2){
            System.out.println("Thread number: 1" );
        }if (IsThread2){
            System.out.println("Thread number: 2" );
        }


        System.out.println("------------------------------");
        System.out.println("MENU");
        System.out.println("a - enter your account id");
        System.out.println("b - pay 20 to your account");
        System.out.println("c - withdrew 30 from your account");
        System.out.println("d - abort your action");
        System.out.println("e - commit your action");
        System.out.println("g - check your balance");
        System.out.println("h - withdrew 0.00 from your account(for testing)");
        System.out.println("i - switch to other device thread(before your switch you have to use 'j' creat thread 2)");
        System.out.println("j - creat device thread 2 and switch to thread");
        System.out.println("f - leave the bank");
        System.out.println("------------------------------");
        System.out.println("Type a letter and press Enter");

    }





//Create a new thread that will check if the data in the bank database is empty
    public static Thread generateThread(int threadID, HashMap hashMap){
        return new Thread(() ->{


            if (!hashMap.isEmpty()){
                BankMenu();
                System.out.println("you can start your operation");
            }else {
                System.out.println("there is no data in the bank system");
            }








        });


    }



//main method, used to create and start a new thread
    public static void main(String[] args) {


        hashmap2.put("account1", 50.00);
        hashmap2.put("account2", 0.00);


      t = new ArrayList<>(2);




        t.add(generateThread(1, hashmap2));
        t.add(generateThread(2, hashmap2));



        t.get(0).start();



    }
}

/*
*
*
* The program can provide the total change of the account after the transaction is submitted and does not allow any thread
*  and any account to be overdrawn. In addition, the option to withdraw GBP 0.0 is provided for testing purposes. The
* withdrawal test can be performed when the account balance is 0.
* This program enables the user to choose to log in from different devices (multi-threading). After creating a new thread
* (after logging into the device), you can switch to a different thread for banking operations at any time. The same bank
* account information account1 and account2 are shared between different threads. Users can choose different threads with
* different accounts. Users can see all their actions and the accounts they belong to when committing changes. A single user
* can open concurrent transactions, and multiple users can open concurrent transactions. By default, the user is in thread 1.
* After creating thread 2 as needed, the user can switch threads freely through the bank menu.

 */

