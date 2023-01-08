package uk.ncl.CSC8016.jackbergus.coursework;

import java.sql.Time;
import java.util.*;

import static uk.ncl.CSC8016.jackbergus.coursework.OperationType.*;

public class Operation implements Comparable<Operation> {

    static OperationType type;
    static double amount;
    static Integer time;
    static String accountID;

    static List<Operation> oplist = new ArrayList<>();

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        Operation.accountID = accountID;
    }

    public OperationType getT() {
        return type;
    }

    public void setT(OperationType t) {
        this.type = t;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

     private  Operation(Integer t) {
        assert t != null;
        this.time = t;
        this.setAmount(amount);


    }

    //Additional expanded accountID information
    public  static synchronized Operation Pay(final double amount, final Integer t, String accountID) throws CloneNotSupportedException {
        assert amount >= 0;
        Operation op = new Operation(t);
        op.amount = amount;
        op.type = Pay;
        op.setAccountID(accountID);

        return op;
    }


//Additional expanded accountID information
    public static synchronized Operation Withdraw(double amount, Integer t, String accountID) {
        assert amount >= 0;
        Operation op2 = new Operation(t);
        op2.amount = amount;
        op2.type = Withdraw;
        op2.setAccountID(accountID);

        return op2;
    }


    public static Operation Abort(Integer t) {
        Operation op = new Operation(t);
        op.amount = 0;
        op.type = Abort;
        return op;
    }

    public static Operation Commit(Integer t) {
        Operation op = new Operation(t);
        op.amount = 0;
        op.type = Commit;
        return op;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Double.compare(operation.amount, amount) == 0 && type == operation.type && Objects.equals(time, operation.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, amount, time);
    }

    @Override
    public int compareTo(Operation o) {
        if (o == null) return 1;
        return time.compareTo(o.time);
    }




    public static double cumulative(List<Operation> collection) {
        Collections.sort(collection);
        List<Operation> finalOperations = new ArrayList<>(collection.size());
        double totalAmount = 0;
        for (var x : collection) {
            switch (x.type) {
                case Pay -> totalAmount += x.amount;
                case Withdraw -> totalAmount -= x.amount;
                default -> {
                }
            }
        }
        return totalAmount;
    }


//Return a String containing all the information of this Operation object to the Bank operator and add it to the log list.
    public synchronized String toString() {

        return "Time:  " + time + "     |Account ID:  " + getAccountID() + "     | Type:  " + getT() + "     |   Amount:  " + getAmount();
    }

}
