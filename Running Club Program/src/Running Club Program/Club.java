package csc8011;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// the club class, including the information of club,statistic information, runner list,
// and transform the parameter with other class by getter setter.
// there are methods for split the csv file to create runner list,
// find the fastest runner, figure out the average time and change the second time to the minutes time.
public class Club {
    /*field*/
    private String ClubName;
    private String FastRunner;
    private String FastRunnerID;
    private int FastRunnerTime;
    private int AverageTime;



    /*constructor*/
    public Club() {

    }


    public Club(String clubName, String fastRunner, String fastRunnerID, int averageTime,  int fastRunnerTime) {
        this.ClubName = clubName;
        this.FastRunner = fastRunner;
        this.FastRunnerID = fastRunnerID;
        this.FastRunnerTime = fastRunnerTime;
        this.AverageTime = averageTime;
    }

    /*getter and setter*/
    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String clubName) {
        ClubName = clubName;
    }

    public String getFastRunner() {
        return FastRunner;
    }

    public void setFastRunner(String fastRunner) {
        FastRunner = fastRunner;
    }

    public String getFastRunnerID() {
        return FastRunnerID;
    }

    public void setFastRunnerID(String fastRunnerID) {
        FastRunnerID = fastRunnerID;
    }

    public int getFastRunnerTime() {
        return FastRunnerTime;
    }

    public void setFastRunnerTime(int fastRunnerTime) {
        FastRunnerTime = fastRunnerTime;
    }

    public int getAverageTime() {
        return AverageTime;
    }

    public void setAverageTime(int averageTime) {
        AverageTime = averageTime;
    }



    /*This method is used ArrayList to divide the rows and columns in the csv table into single values and distinguish the attributes of each value. Finally, all are printed out. */
    public ArrayList<Runner> ShowRunnerList( File f) {
        int i = 0;
        ArrayList<Runner> RunnerList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));

            String line;
            Scanner scanner;
            while ((line = reader.readLine()) != null) {
                Club cl = new Club();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                Runner run = new Runner();

                while (scanner.hasNext()) {
                    String text = scanner.next();
                    if (i == 0)
                        run.setMebershipID(text);
                    else if (i == 1)
                        run.setRunner_Name(text);
                    else if (i == 2)
                        run.setTime(Integer.parseInt(text));
                    else
                        System.out.println("Wrong data" + text);
                        cl.setClubName("Bulls");
                    i++;
                }
                i = 0;

                RunnerList.add(run);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RunnerList;
    }
    /*This method trying to select the time in csv and figure out who use the least time */
    public void RunningFast( BufferedReader reader){
        int i = 0;
        int spead1 ;
        int spead2 = 99999;
        try {
            String line;
            Scanner scanner;
            while ((line = reader.readLine()) != null) {
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                Runner run = new Runner();

                while (scanner.hasNext()) {
                    String text = scanner.next();
                    if (i == 0)
                        run.setMebershipID(text);
                    else if (i == 1)
                        run.setRunner_Name(text);
                    else if (i == 2)
                        run.setTime(Integer.parseInt(text));
                    else
                        System.out.println("Wrong data" + text);

                    spead1 = run.getTime();
                    if (spead1 < spead2 && i == 2) {
                        setFastRunnerTime(run.getTime());
                        spead2 = spead1;
                        setFastRunner(run.getRunner_Name());
                        setFastRunnerID(run.getMebershipID());
                        setFastRunnerTime(spead2);
                    }
                    i++;
                }
                i = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*We select all the time value from csv, combined them and divide by how many
      people in the list to figure out the average time*/
public void AverageT( File f) {

    int totaltime = 0;
    int i = 0;
    int ForeTimesPeopleNumber = 0;

    try {
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line ;
        Scanner scanner ;
        while ((line = reader.readLine()) != null) {
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            Runner run = new Runner();
            while (scanner.hasNext()) {
                String text = scanner.next();
                if (i == 2)
                    run.setTime(Integer.parseInt(text));
                totaltime +=run.getTime();
                i++;
            }
            ForeTimesPeopleNumber++;
            i=0;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    setAverageTime(totaltime/ForeTimesPeopleNumber) ;

}
    /*This method can be used to transform the time format from second to minute and second*/
public String TimeTransform(int time){
    int minute;
    int second;

    minute = time/60;
    second = time-minute*60;
    return minute+"m "+second+"s";
}

    /*The Override can send string data to the clubIO  */
    @Override
    public String toString(){
        return
                "Fast Runner is: "+getFastRunner()+"("+getFastRunnerID()+")"+", Time:"+TimeTransform(getFastRunnerTime())+
                "\nAverage time:"+TimeTransform(getAverageTime());

    }
}
