package csc8011;

// the runner class, including the information of runner, and transform the parameter with other class by getter setter.
public class Runner {
/*field*/
     private String MebershipID;
     private String Runner_Name;
     private int Time;

     /*Constructor*/
     public Runner(String mebershipID, String runner_Name, int time) {
          this.MebershipID = mebershipID;
          this.Runner_Name = runner_Name;
          this.Time = time;
     }
     /*Getter and Setter*/
     public Runner() {

     }

     public String getMebershipID() {
          return MebershipID;
     }

     public void setMebershipID(String mebershipID) {
          MebershipID = mebershipID;
     }

     public String getRunner_Name() {
          return Runner_Name;
     }

     public void setRunner_Name(String runner_Name) {
          Runner_Name = runner_Name;
     }

     public int getTime() {
          return Time;
     }

     public void setTime(int time) {
          this.Time = time;
     }
     /*The Override can send string data to the clubIO class or club class  */
     @Override
     public String toString(){
          return "Member ID: "+getMebershipID()+" Name: "+getRunner_Name()+" Time "+ getTime()+"\n";

    }

}