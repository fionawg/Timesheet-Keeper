import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Collections;

public class Commands {
    private double totalHours;
    private ArrayList<Day> list;

    public Commands() {
        totalHours = 0;
        list = new ArrayList<Day>();
    }

    public void addHours(String day, String timeIn, String timeOut, Commands commands){
        Day d = new Day(day, timeIn, timeOut);
        list.add(d);
        Day.check(day, timeIn, timeOut, d.getHours(), commands);
        totalHours += d.getHours();
        sortList();
        save();
    }

    public void removeHours(int num){
        num--;
        totalHours -= list.remove(num).getHours();
        save();
    }

    public void clearTimesheet(){
        for (int i = 0; i < list.size(); i++){
            totalHours = 0;
            list.remove(i);
            i--;
        }
        save();
    }

    public void printMenu() {
        System.out.print("\n(a)dd hours\n");
        System.out.print("(d)elete hours\n");
        System.out.print("(c)lear timesheet\n");
        System.out.print("(p)rint timesheet for everything\n");
        System.out.print("(pr)int timesheet for this week\n");
        System.out.print("(pri)nt timesheet for specific date\n");
        System.out.print("(q)uit");
        System.out.print("\nwhat would you like to do? ");
    }

    public void printList() {
        System.out.print("\n");
        int count = 1;
        double totalHours = 0.0;
        for (Day d : list) {
            System.out.print("(" + count + ") " + d.getName() + " // " + d.getHours() + " hour(s) from " + d.getTimeIn()
                    + " to " + d.getTimeOut() + "\n");
            count++;
            totalHours += d.getHours();
        }
        System.out.print("total hours worked: " + totalHours + "\n");
    }

    public void printInfoDate(String date) {
        int count = 1;
        double totalHours = 0.0;
        for (Day d : list) {
            if (d.getName().equals(date)) {
                System.out.print("(" + count + ") " + d.getHours() + " hour(s) from " + d.getTimeIn()
                        + " to " + d.getTimeOut() + "\n");
                count++;
                totalHours += d.getHours();
            }
        }
        System.out.print("total hours worked on " + date + ": " + totalHours + "\n");
    }

    public void printInfoWeek() {
        System.out.print("\n");
        int count = 1;
        double totalHours = 0.0;
        String str = "";
        String date;
        String temp;
        int num;
        int month;
        int year;
        GregorianCalendar g = new GregorianCalendar();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        date = format1.format(c.getTime());
        num = Integer.parseInt(date.substring(8));
        month = Integer.parseInt(date.substring(5, 7));
        year = Integer.parseInt(date.substring(0, 4));
        //starts on monday (CTE)
        if (c.get(Calendar.DAY_OF_WEEK) == 1){
            num -= 6;
        } else {
            num -= (c.get(Calendar.DAY_OF_WEEK) - 2);
        }
        //starts on sunday
        //num -= (c.get(Calendar.DAY_OF_WEEK) - 1);
        if (num < 1){
            month--;
            if (month == 0){
                year--;
                month = 12;
            }
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                num = 31 - Math.abs(num);
            } else if (month == 2){
                if (g.isLeapYear(year)){
                    num = 29 - Math.abs(num);
                } else {
                    num = 28 - Math.abs(num);
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11){
                num = 30 - Math.abs(num);
            }
        }
        temp = "" + month;
        if (temp.length() == 1){
            str += "0" + temp + "/";
        } else {
            str += temp + "/";
        }
        temp = "" + num;
        if (temp.length() == 1){
            str += "0" + temp + "/";
        } else {
            str += temp + "/";
        }
        temp = "" + year;
        str += temp;
        for (int i = 0; i < 7; i++) {
            for (Day d : list) {
                if (d.getName().equals(str)) {
                    System.out.print("(" + count + ") " + d.getName() + " // " + d.getHours() + " hour(s) from " + d.getTimeIn()
                            + " to " + d.getTimeOut() + "\n");
                    count++;
                    totalHours += d.getHours();
                }
            }
            num = Integer.parseInt(str.substring(3, 5)) + 1;
            month = Integer.parseInt(str.substring(0, 2));
            year = Integer.parseInt(str.substring(6));
            if (str.substring(0, 2).equals("01") || str.substring(0, 2).equals("03") || str.substring(0, 2).equals("05") || str.substring(0, 2).equals("07") || str.substring(0, 2).equals("08") || str.substring(0, 2).equals("10") || str.substring(0, 2).equals("12")) {
                if (num > 31) {
                    num = num - 31;
                    if (month == 12) {
                        month = 1;
                        year++;
                    } else {
                        month++;
                    }
                }
            } else if (str.substring(0, 2).equals("02")) {
                if (g.isLeapYear(year)) {
                    if (num > 29) {
                        num = num - 29;
                        if (month == 12) {
                            month = 1;
                            year++;
                        } else {
                            month++;
                        }
                    }
                } else {
                    num = num - 28;
                    if (month == 12) {
                        month = 1;
                        year++;
                    } else {
                        month++;
                    }
                }
            } else if (str.substring(0, 2).equals("04") || str.substring(0, 2).equals("06") || str.substring(0, 2).equals("09") || str.substring(0, 2).equals("11")) {
                if (num > 30) {
                    num = num - 30;
                    if (month == 12) {
                        month = 1;
                        year++;
                    } else {
                        month++;
                    }
                }
            }
            String numToStr = "" + num;
            String monthToStr = "" + month;
            if (numToStr.length() == 1) {
                if (monthToStr.length() == 1) {
                    str = "0" + month + "/0" + num + "/" + year;
                } else {
                    str = month + "/0" + num + "/" + year;
                }
            } else {
                if (monthToStr.length() == 1) {
                    str = "0" + month + "/" + num + "/" + year;
                } else {
                    str = month + "/" + num + "/" + year;
                }
            }
        }
        System.out.print("total hours: " + totalHours + "\n");
    }

    public double getTotalHours() {
        return totalHours;
    }

    public ArrayList<Day> getList() {
        return list;
    }

    private void sortList() {
        int index = -1;
        int count = 0;
        int earliestYear;
        int earliestMonth;
        int earliestDay;
        int earliestHr;
        int earliestMin;
        for (int i = 0; i < list.size(); i++) {
            earliestYear = Integer.MAX_VALUE;
            earliestMonth = Integer.MAX_VALUE;
            earliestDay = Integer.MAX_VALUE;
            earliestHr = Integer.MAX_VALUE;
            earliestMin = Integer.MAX_VALUE;
            for (int j = i; j < list.size(); j++) {
                int compYear = Integer.parseInt(list.get(j).getName().substring(6));
                int compMonth = Integer.parseInt(list.get(j).getName().substring(0, 2));
                int compDay = Integer.parseInt(list.get(j).getName().substring(3, 5));
                int compHr = Integer.parseInt(list.get(j).getMilTimeIn().substring(0, 2));
                int compMin = Integer.parseInt(list.get(j).getMilTimeIn().substring(3));
                if (compYear < earliestYear) {
                    earliestYear = compYear;
                    earliestMonth = compMonth;
                    earliestDay = compDay;
                    earliestHr = compHr;
                    earliestMin = compMin;
                    index = j;
                }
                if (compYear == earliestYear) {
                    if (compMonth < earliestMonth) {
                        earliestMonth = compMonth;
                        earliestDay = compDay;
                        earliestHr = compHr;
                        earliestMin = compMin;
                        index = j;
                    }
                    if (compMonth == earliestMonth) {
                        if (compDay < earliestDay) {
                            earliestDay = compDay;
                            earliestHr = compHr;
                            earliestMin = compMin;
                            index = j;
                        }
                        if (compDay == earliestDay) {
                            if (compHr < earliestHr) {
                                earliestHr = compHr;
                                earliestMin = compMin;
                                index = j;
                            }
                            if (compHr == earliestHr) {
                                if (compMin < earliestMin) {
                                    earliestMin = compMin;
                                    index = j;
                                }
                            }
                        }
                    }
                }
            }
            Collections.swap(list, count, index);
            count++;
        }
    }

    private void save() {
        try {
            File f = new File("src/saved.data");
            f.createNewFile();
            FileWriter fw = new FileWriter("src/saved.data");
            sortList();
            for (Day d : list) {
                fw.write(d.getName() + "\n");
                fw.write(d.getTimeIn() + "\n");
                fw.write(d.getTimeOut() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to create file");
            e.printStackTrace();
        }
    }
}