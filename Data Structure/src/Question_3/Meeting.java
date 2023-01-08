package Question_3;

public class Meeting {
    private String time;
    private String location;
    private String subject;

    public Meeting(String time, String location, String subject) {
        this.time = time;
        this.location = location;
        this.subject = subject;
    }
    public Meeting() {

    }

    public void setTime(int hours, int minutes) throws Exception {
    boolean in_range = hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 60;
        if (!in_range){
        throw new RuntimeException("Your enter is out of range, please try again");
    }else {
        String hoursS = Integer.toString(hours);
        String minutesS = Integer.toString(minutes);
        String result=  hoursS + ":" + minutesS;

        this.time = result;
    }
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void printDetails(){
    System.out.println("Meeting in room "+ location + " at " + time + "; Subject: " + subject);
    }


}
