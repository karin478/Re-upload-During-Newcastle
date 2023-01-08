package csc8011;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

//This class is used to create a command menu for the user, user can use number to choose which option they want.
public class ClubIo {
    /*field*/
    public static String line;
    public static String line2;
    public static boolean condition1 = true;
    public static boolean condition3 = false;
    public static boolean condition4 = false;

    /*Main method for running the program which can transmission parameter with Runner and club class*/
    public static void main(String[] args) throws IOException {
        /*loop and decision make for the user commander
         * totally 5 option:
         * 1. allow the user enter the name of club
         * 2. allow the user read the file and catch the error
         * 3. allow the user check the list of all the runner
         * 4. allow the user check the statistic information
         * 5. allow the user exist the program
         * if user didn't enter the team name or read the file they are unable to check the runner list and statistic data
         * after any option user made they can return the main menu*/
        Club cl = new Club();
        File f = null;
        BufferedReader reader = null;
        while (condition1) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to the Club System" +
                    "\nEnter 1 to set the name of club" +
                    "\nEnter 2 to read the race.csv file" +
                    "\nEnter 3 to show the list of all runner" +
                    "\nEnter 4 to check the statistic of runner list" +
                    "\nEnter 5 to exit the program");
            line = sc.next();
            if (line.equals("1")) {
                System.out.println("Please enter the Name of the club  ");
                    line2 = sc.next();
                     cl.setClubName(line2);
                        System.out.println("Name received, redirecting to the menu......");
                System.out.println("================================================================");
                        condition4=true;
                }
            // reading the file. If there is problem, the try catch will catch it
            else if (line.equals("2")) {
                try {
                    System.out.println("Trying to read the file, please wait");
                    f = new File(Club.class.getResource(
                            "race.csv").getFile());
                    reader = new BufferedReader(new FileReader(f));
                    System.out.println("Successful reading the file, redirecting to the menu......");
                    System.out.println("================================================================");
                    condition3 = true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            //if user enter number 3 after they enter the name and read the file
            else if (line.equals("3") && condition3 && condition4) {

                System.out.println("Club name: " + cl.getClubName());
                for (int i = 0; i < cl.ShowRunnerList(f).size(); i++) {
                    System.out.print(cl.ShowRunnerList(f).get(i));
                }
                System.out.println("\nRunner List has printed, redirecting to main menu......");
                System.out.println("================================================================");

            }
            //if user enter number 3 or 4 before they enter the name and read the file
            else if ((line.equals("3")||line.equals("4")) && !(condition3&&condition4)){
                System.out.println("You didn't name the team or read the file yet, Please read the file and set the club name first. Redirecting to the menu......");
                System.out.println("================================================================");
            }
            //if user enter number 4 after they enter the name and read the file
            else if (line.equals("4") && condition3 && condition4) {
                cl.RunningFast(reader);
                cl.AverageT(f);
                System.out.println(cl);
                System.out.println("\nStatistic data has printed, redirecting to main menu......");
                System.out.println("================================================================");
            }
            else if (line.equals("5")) {
                System.out.println("Thank you for your using, see you soon");
                break;
            }
            else {
                System.out.println("You should enter number from 1 to 5, Please try again");
                System.out.println("================================================================");
            }
        }
    }
}

