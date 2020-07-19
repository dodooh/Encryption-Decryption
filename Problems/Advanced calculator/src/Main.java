import java.util.Arrays;

/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        String[] strlist = Arrays.copyOfRange(args, 1,args.length);
        int[] intlist = Arrays.stream(strlist)
                .mapToInt(Integer::parseInt)
                .toArray();
        switch (operator) {
            case "MAX":
                System.out.println(Arrays.stream(intlist).max().getAsInt());
                break;
            case "MIN":
                System.out.println(Arrays.stream(intlist).min().getAsInt());
                break;
            case "SUM":
                System.out.println(Arrays.stream(intlist).sum());
                break;
            default:
                System.out.println("Wrong input");
        }
    }
}