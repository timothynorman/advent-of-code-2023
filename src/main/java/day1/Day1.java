package day1;

import java.util.List;
import utils.InputReader;

public class Day1 {

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./src/main/java/day1/input");
        List<String> input = inputReader.toLines();

        int total = 0;

        for (String line : input) {
            String concat = searchFromFront(line) + searchFromEnd(line);
            total += Integer.parseInt(concat);
        }

        System.out.printf("Part 1: %d", total);

    }

    private static String searchFromFront(String line) {
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (Character.isDigit(c)) return Character.toString(c);
        }
        return null;
    }

    private static String searchFromEnd(String line) {
        for (int i = line.length() - 1; i > -1; i--) {
            char c = line.charAt(i);
            if (Character.isDigit(c)) return Character.toString(c);
        }
        return null;
    }

}
