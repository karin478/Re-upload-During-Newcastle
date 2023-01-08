package Question_3;

public class Main_Q3 {

    public static void main(String[] args)  {

        Meeting meet = new Meeting();
        try {
            meet.setTime(12,20);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("time out of limit");
        }
        meet.setLocation("usb 106");
        meet.setSubject("math");
        meet.printDetails();
        System.out.println("=======================================");
        immutable imm = new immutable();
        try {
            imm.setTime(17,20);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("time out of limit");
        }
        imm.setLocation("301");
        imm.setSubject("computing");
        imm.printDetails();

    }




}
