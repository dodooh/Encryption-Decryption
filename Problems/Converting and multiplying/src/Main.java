import java.util.Scanner; 

class Main {
    public static long timeTen(String s) {
        return Integer.parseInt(s) * 10;
    } 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        String s = sc.nextLine();
        while (!s.equals("0")) {
            try {
                timeTen(s);
            } catch (NumberFormatException e) {
                System.out.println("Invalid user input: " + s);
            }
            s = sc.nextLine();
        }
    }
}
