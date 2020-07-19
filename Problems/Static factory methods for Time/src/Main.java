import java.util.Scanner;

class Time {

    int hour;
    int minute;
    int second;

    public static Time noon() {
        // write your code here
        Time result = new Time();
        result.hour = 12;
        result.minute = 0;
        result.second = 0;
        return result;

    }

    public static Time midnight() {
        // write your code here
        Time result = new Time();
        result.hour = 0;
        result.minute = 0;
        result.second = 0;
        return result;
    }

    public static Time ofSeconds(long seconds) {
        // write your code here
        Time result = new Time();
        result.second = (int)seconds % 60;
        seconds/= 60;
        result.minute = (int)seconds % 60;
        seconds/= 60;
        result.hour = (int)seconds % 24;
        return result;
    }

    public static Time of(int hour, int minute, int second) {
        // write your code here
        Time result = new Time();
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59)
        || (second < 0 || second > 59)) {
            return null;
        }
        result.hour = hour;
        result.minute = minute;
        result.second = second;
        return result;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String type = scanner.next();
        Time time = null;

        switch (type) {
            case "noon":
                time = Time.noon();
                break;
            case "midnight":
                time = Time.midnight();
                break;
            case "hms":
                int h = scanner.nextInt();
                int m = scanner.nextInt();
                int s = scanner.nextInt();
                time = Time.of(h, m, s);
                break;
            case "seconds":
                time = Time.ofSeconds(scanner.nextInt());
                break;
            default:
                time = null;
                break;
        }

        if (time == null) {
            System.out.println(time);
        } else {
            System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
        }
    }
}