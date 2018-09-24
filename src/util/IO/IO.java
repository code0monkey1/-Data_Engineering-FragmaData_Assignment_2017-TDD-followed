package util.IO;

import java.util.Scanner;

public final class IO {

    public static final int inputInt() {
        Scanner reader = new Scanner(System.in);

        int choice = 0;
        String option = "";

        try {
            option = reader.nextLine();
            choice = Integer.parseInt(option);

        } catch (NumberFormatException e) {

            throw new NumberFormatException(String.format("The input : \"" +
                    " %s \" is not a number !!", option));
        }
        return choice;
    }
}
