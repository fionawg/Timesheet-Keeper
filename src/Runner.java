import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Runner{
    public static void main (String[] args){
        try{
            File f = new File("src/saved.data");
            Scanner s = new Scanner(f);
            Commands commands = new Commands();
            int line = 1;
            String name = "";
            String timeIn = "";
            String timeOut = "";
            System.out.println("welcome back to timesheet keeper!");
            int count = 0;
            while (s.hasNextLine()){
                if (line % 3 == 0){
                    timeOut = s.nextLine();
                    commands.addHours(name, timeIn, timeOut, commands);
                    count = 1;
                } else if (count == 2){
                    timeIn = s.nextLine();
                    count = 3;
                } else {
                    name = s.nextLine();
                    count = 2;
                }
                line++;
            }
            s.close();
            Scanner scanner = new Scanner(System.in);
            commands.printMenu();
            String choice = scanner.nextLine();
            String date = "";
            while (choice.equals("a") || choice.equals("d") || choice.equals("c") || choice.equals("p") || choice.equals("pr") || choice.equals("pri")){
                if (choice.equals("a")){
                    try {
                        System.out.print("\ndate: ");
                        date = scanner.nextLine();
                        System.out.print("time in: ");
                        timeIn = scanner.nextLine();
                        System.out.print("time out: ");
                        timeOut = scanner.nextLine();
                        commands.addHours(date, timeIn, timeOut, commands);
                        boolean x = false;
                        for (Day d : commands.getList()){
                            if (d.getName().equals(date) && d.getTimeIn().equals(timeIn) && d.getTimeOut().equals(timeOut)){
                                x = true;
                                break;
                            }
                        }
                        if (x){
                            System.out.print("you successfully added " + date + " to the timesheet.\n");
                        } else {
                            System.out.print("you were unable to add " + date + " to the timesheet.\n");
                        }
                    } catch (Exception e){
                        System.out.println("something you entered was formatted wrong. please try again.");
                    }
                    commands.printMenu();
                    choice = scanner.nextLine();
                }
                if (choice.equals("d")){
                    commands.printList();
                    System.out.print("which number do you want to remove? ");
                    try {
                        int temp = Integer.parseInt(scanner.nextLine());
                        commands.removeHours(temp);
                        System.out.print("you successfully removed number (" + temp + ") from the timesheet.\n");
                    } catch (Exception ex){
                        System.out.println("please enter a valid number.");
                    }
                    commands.printMenu();
                    choice = scanner.nextLine();
                }
                if (choice.equals("c")){
                    commands.clearTimesheet();
                    System.out.print("\nyou successfully cleared the timesheet.\n");
                    commands.printMenu();
                    choice = scanner.nextLine();
                }
                if (choice.equals("p")){
                    commands.printList();
                    commands.printMenu();
                    choice = scanner.nextLine();
                }
                if (choice.equals("pr")){
                    commands.printInfoWeek();
                    commands.printMenu();
                    choice = scanner.nextLine();
                }
                if (choice.equals("pri")){
                    System.out.print("\ndate: ");
                    commands.printInfoDate(scanner.nextLine());
                    commands.printMenu();
                    choice = scanner.nextLine();
                }
            }
            if (!choice.equals("q")){
                System.out.print("\nwe don't know what this means, but your info has been saved.");
            } else {
                System.out.print("\nyou successfully exited this program.");
            }
            scanner.close();
        } catch (FileNotFoundException e){
            System.out.println("welcome to timesheet keeper!\n");
            System.out.print("here are the main things you need to know:\n");
            System.out.println("- when entering times, enter it in this format: hh:mm am (or pm). for example, 12:05 am or 01:30 pm.");
            System.out.println("- when entering dates, enter it in this format: mm/dd/yyyy. for example, 01/30/2022 or 12/05/1999.\n");
            String date = "";
            String timeIn = "";
            String timeOut = "";
            Scanner s = new Scanner(System.in);
            Scanner nums = new Scanner(System.in);
            System.out.print("to get started enter a date to add hours: ");
            date = s.nextLine();
            System.out.print("input the time you started: ");
            timeIn = s.nextLine();
            System.out.print("input the time you finished: ");
            timeOut = s.nextLine();
            Commands commands = new Commands();
            try {
                commands.addHours(date, timeIn, timeOut, commands);
                boolean x = false;
                for (Day d : commands.getList()){
                    if (d.getName().equals(date) && d.getTimeIn().equals(timeIn) && d.getTimeOut().equals(timeOut)){
                        x = true;
                    }
                }
                if (x){
                    System.out.print("you successfully added " + date + " to the timesheet.\n");
                } else {
                    System.out.print("you were unable to add " + date + " to the timesheet.\n");
                }
            } catch (Exception ex){
                System.out.println("something you entered was formatted wrong. please try again.");
            }
            commands.printMenu();
            String choice = s.nextLine();
            while (choice.equals("a") || choice.equals("d") || choice.equals("c") || choice.equals("p") || choice.equals("pr") || choice.equals("pri")){
                if (choice.equals("a")){
                    try {
                        System.out.print("\ndate: ");
                        date = s.nextLine();
                        System.out.print("time in: ");
                        timeIn = s.nextLine();
                        System.out.print("time out: ");
                        timeOut = s.nextLine();
                        commands.addHours(date, timeIn, timeOut, commands);
                        boolean x = false;
                        for (Day d : commands.getList()){
                            if (d.getName().equals(date) && d.getTimeIn().equals(timeIn) && d.getTimeOut().equals(timeOut)){
                                x = true;
                                break;
                            }
                        }
                        if (x){
                            System.out.print("you successfully added " + date + " to the timesheet.\n");
                        } else {
                            System.out.print("you were unable to add " + date + " to the timesheet.\n");
                        }
                    } catch (Exception ex){
                        System.out.println("something you entered was formatted wrong. please try again.");
                    }
                    commands.printMenu();
                    choice = s.nextLine();
                }
                if (choice.equals("d")){
                    commands.printList();
                    System.out.print("which one do you want to remove? ");
                    try {
                        int temp = Integer.parseInt(s.nextLine());
                        commands.removeHours(temp);
                        System.out.print("you successfully removed number (" + temp + ") from the timesheet.\n");
                    } catch (Exception ex){
                        System.out.println("please enter a valid number.");
                    }
                    commands.printMenu();
                    choice = s.nextLine();
                }
                if (choice.equals("c")){
                    System.out.println();
                    commands.clearTimesheet();
                    System.out.print("you successfully cleared the timesheet.\n");
                    commands.printMenu();
                    choice = s.nextLine();
                }
                if (choice.equals("p")){
                    commands.printList();
                    commands.printMenu();
                    choice = s.nextLine();
                }
                if (choice.equals("pr")){
                    commands.printInfoWeek();
                    commands.printMenu();
                    choice = s.nextLine();
                }
                if (choice.equals("pri")){
                    System.out.println();
                    System.out.print("date: ");
                    commands.printInfoDate(s.nextLine());
                    commands.printMenu();
                    choice = s.nextLine();
                }
            }
            if (!choice.equals("q")){
                System.out.print("\nwe don't know what this means, but your info has been saved.");
            } else {
                System.out.print("\nyou successfully exited this program.");
            }
            s.close();
        }
    }
}