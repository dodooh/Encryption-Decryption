import java.util.*;

public class Main {
    public static boolean isVowels(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
    }
    public static void main(String[] args) {
        String str = new Scanner(System.in).next();
        int sum = 0;
        char prev = str.charAt(0);
        int count = 1;
        for(int i = 1; i < str.length(); ++i) {
            if ((isVowels(str.charAt(i)) && isVowels(prev))
                    || (!isVowels(str.charAt(i)) && !isVowels(prev))){
                count++;
            } else {
                if (count >= 3) {
                    int div = (int)Math.ceil((double)count / 2) - 1;
                    sum += div;
                }
                count = 1;
            }
            prev = str.charAt(i);
        }
        if (count >= 3) {
            int div = (int)Math.ceil((double)count / 2) - 1;
            sum += div;
        }
        System.out.println(sum);
        
    }
}
