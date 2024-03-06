import java.util.Objects;
import java.util.Scanner;

public class DrawF {


    public static void main(String[] args) {
        // char M = 'a';
        int L = 0;
        try {
            // Scanner myobj = new Scanner(System.in);
            //  System.out.println("Enter value: ");
            //  args[0] = String.valueOf(myobj.next().charAt(0));
            if (!args[0].equals("c") && !args[0].equals("w") && !args[0].equals("f") && !args[0].equals("g")) {
                throw new Exception("Wrong Value");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //Scanner myobj2 = new Scanner(System.in);
            // System.out.println("Enter any number between 3-20: ");
            // args[1] = String.valueOf(myobj2.nextInt());
            L = Integer.parseInt(args[1]);
            if (L < 3 || L > 20 || (L != (int) L)) {
                throw new Exception("Wrong Value or type");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String M = args[0];
        if (M.equals("c")) {
            for (int i = 1; i <= L; i++) {
                if (i == 1) {
                    for (int j = 1; j <= L - 1; j++) {
                        System.out.print("*");
                    }
                    System.out.println("*");
                } else if (i == L / 2 + 1) {
                    for (int j = 1; j <= L / 2; j++) {
                        System.out.print("*");
                    }
                    System.out.println("*");
                } else {
                    System.out.println("*");
                }
            }
        }
    }
}
