public class Day {
    private String name;
    private String timeIn;
    private String timeOut;
    private String milTimeIn;
    private String milTimeOut;
    private double hours;
    private Commands commands;

    public Day (String name, String timeIn, String timeOut, Commands commands){
        this.name = name;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        toMilitary();
        hours = calculateHours();
        this.commands = commands;
    }

    public static void check(String name, String timeIn, String timeOut, double hours, Commands commands){
        if (hours == 0.0){
            System.out.println("this is inbetween two days; please enter two entries.");
            int index = Integer.MIN_VALUE;
            for (int i = 0; i < commands.getList().size(); i++){
                if (commands.getList().get(i).getName().equals(name) && commands.getList().get(i).getTimeIn().equals(timeIn) && commands.getList().get(i).getTimeOut().equals(timeOut)){
                    index = i;
                }
            }
            commands.removeHours(index + 1);
        }
    }

    public Day (String name, String timeIn, String timeOut){
        this.name = name;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        toMilitary();
        hours = calculateHours();
    }

    private void toMilitary (){
        String time = timeIn.substring(0, 5);
        int change = 0;
        if (timeIn.substring(6).equals("am")){
            if (time.substring(0, 2).equals("12")){
                time = "00" + time.substring(2);
            }
            milTimeIn = time;
        } else {
            if (time.substring(0, 2).equals("12")){
                change = 12;
            } else {
                change = Integer.parseInt(time.substring(0, 2)) + 12;
            }
            milTimeIn = "" + change + time.substring(2);
        }
        time = timeOut.substring(0, 5);
        if (timeOut.substring(6).equals("am")){
            if (time.substring(0, 2).equals("12")){
                time = "00" + time.substring(2);
            }
            milTimeOut = time;
        } else {
            if (time.substring(0, 2).equals("12")){
                change = 12;
            } else {
                change = Integer.parseInt(time.substring(0, 2)) + 12;
            }
            milTimeOut = "" + change + time.substring(2);
        }
    }

    private double calculateHours (){
        String str = "";
        double tIn = 0.0;
        double tOut = 0.0;
        String hrIn = milTimeIn.substring(0, 2);
        String minIn = milTimeIn.substring(3);
        minIn = "." + minIn;
        minIn = (Double.parseDouble(minIn) * ((double)5/3)) + "";
        minIn = minIn.substring(minIn.indexOf(".") + 1);
        str = hrIn + "." + minIn;
        tIn = Double.parseDouble(str);
        String hrOut = milTimeOut.substring(0, 2);
        String minOut = milTimeOut.substring(3);
        minOut = "." + minOut;
        minOut = (Double.parseDouble(minOut) * ((double)5/3)) + "";
        minOut = minOut.substring(minOut.indexOf(".") + 1);
        str = hrOut + "." + minOut;
        tOut = Double.parseDouble(str);
        String inAmOrPm = timeIn.substring(6);
        String outAmOrPm = timeOut.substring(6);
        if (inAmOrPm.equals("pm") && outAmOrPm.equals("am") && tOut == 0.0){
            return ((24.0 - tIn)*100.00)/100.00;
        } else if (inAmOrPm.equals("pm") && outAmOrPm.equals("am")){
            return 0;
        }
        return ((tOut - tIn)*100.00)/100.00;
    }

    public String getName (){
        return name;
    }

    public double getHours (){
        return hours;
    }

    public String getTimeIn (){
        return timeIn;
    }

    public String getTimeOut (){
        return timeOut;
    }

    public String getMilTimeIn (){
        return milTimeIn;
    }

    public String getMilTimeOut (){
        return milTimeOut;
    }
}