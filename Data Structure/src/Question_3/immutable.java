package Question_3;

import java.util.ArrayList;

public class immutable extends Meeting {
    private String time;
    private String location;
    private String subject;

    public immutable(String time, String location, String subject, String time1, String location1, String subject1) {
        super(time, location, subject);
        this.time = time1;
        this.location = location1;
        this.subject = subject1;
    }

    public immutable(String time, String location, String subject) {
        this.time = time;
        this.location = location;
        this.subject = subject;
    }
    public immutable(){}



    public String getTime() {
        return time;
    }
    @Override
    public void setTime(int hours, int minutes) throws Exception {
        boolean in_range = hours >= 0 && hours <= 24 && minutes >= 0 && minutes <= 60;
        boolean in_range_afternoon = hours >= 12 && hours <= 17;
        assert in_range_afternoon: "this time is not available";
        if (!in_range){
            throw new RuntimeException("Your enter is out of range, please try again");
        }else {
            String hoursS = Integer.toString(hours);
            String minutesS = Integer.toString(minutes);
            String result=  hoursS + ":" + minutesS;

            this.time = result;
        }
    }

    public String getLocation() {
        return location;
    }

    @Override
    public void printDetails() {
        System.out.println("Meeting in room "+ location + " at " + time + "; Subject: " + subject);
    }

    @Override
    public void setLocation(String location) {
        boolean room_contain = false;
        ArrayList<String> room = new ArrayList<String>();
        room.add("204");
        room.add("301");
        if(room.contains(location)){
            room_contain = true;
        }
        assert room_contain: "this room is not available";
        this.location = location;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
