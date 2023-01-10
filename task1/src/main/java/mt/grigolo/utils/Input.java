package mt.grigolo.utils;

import java.util.Scanner;

public class Input {

    static Scanner s = new Scanner(System.in);

    public static int getInt(int min, int max) {
        int input = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                input = Integer.parseInt(getString());
            } catch (NumberFormatException e) {
                System.out.println("Error! Invalid integer value. Try again.");
            }
            if (input < min) {
                System.out.println("Error! Number must be " + min + " or greater.");
            } else if (input > max) {
                System.out.println("Error! Number must be " + max + " or less.");
            } else {
                isValid = true;
            }
        }
        return input;
    }

    public static String getString() {
        return s.nextLine();
    }

    public void closeScanner() {
        s.close();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}