import java.util.Scanner;

class Problem {

    public static void main(String[] args) {
        int count = 0;
        boolean haveTest = false;
        for(int i = 0; i < args.length; i++) {
            if (args[i].equals("test")) {
                haveTest = true;
                break;
            }
            count++;
        }
        if (haveTest) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }
}
