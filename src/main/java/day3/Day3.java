package day3;

import utils.InputReader;

import java.util.ArrayList;

public class Day3 {

    public static ArrayList<ArrayList<Character>> input = new ArrayList<>();
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./src/main/java/day3/input");
         input = inputReader.toCharGrid();

         // Part 1
        System.out.printf("Part 1: %d %n", sumOfPartNumbers());

        // Part 2
        System.out.printf("Part 2: %d %n", sumOfGearRatios());

    }

    /**
     * Calculates the sum of gear ratios, where gear ratios are the product of two
     * (and only two) numbers adjacent to a '*' symbol.
     * @return Sum of gear ratios as an Integer.
     */
    private static Integer sumOfGearRatios() {
        int total = 0;
        for (int row = 0; row < input.size(); row++) {
            for (int column = 0; column < input.getFirst().size(); column++) {
                Character c = input.get(row).get(column);
                if (c.equals('*')) {
                    ArrayList<Integer> parts = searchAroundSymbol(row, column);
                    if (parts.size() == 2) {
                        int gearRatio = 1;
                        for (Integer part : parts) {
                            gearRatio *= part;
                        }
                        total += gearRatio;
                    }
                }
            }
        }
        return total;
    }

    /**
     * Calculates the sum of all part numbers which are adjacent to a symbol.
     * @return Sum of all part numbers adjacent to a symbol.
     */
    private static Integer sumOfPartNumbers() {
        int total = 0;
        for(int row = 0; row < input.size(); row++) {
            for (int column = 0; column < input.getFirst().size(); column++) {
                Character c = input.get(row).get(column);
                if (isSymbol(c)) {
                    for (Integer part : searchAroundSymbol(row, column)) total += part;
                }
            }
        }
        return total;
    }

    /**
     * Searches one space in all directions (including diagonally) from a given coordinate
     * and returns all part numbers with at least one digit touching the given coordinate.
     * @param row Row number of given coordinate.
     * @param column Column number of given coordinate.
     * @return ArrayList of part numbers as Integers.
     */
    private static ArrayList<Integer> searchAroundSymbol(int row, int column) {
        ArrayList<Integer> machineParts = new ArrayList<>();
        int minRow = 0;
        if (row - 1 > minRow) minRow = row - 1;

        int maxRow = input.size() - 1;
        if (row + 1 < maxRow) maxRow = row + 1;

        int minCol = 0;
        if (column - 1 > minCol) minCol = column - 1;

        int maxCol = input.getFirst().size() - 1;
        if (column + 1 < maxCol) maxCol = column + 1;

        for (int i = minRow; i <= maxRow; i++) {
            int previousNumber = -1;
            for (int j = minCol; j <= maxCol; j++) {
                int number;
                if (Character.isDigit(input.get(i).get(j))) {
                    number = getNumber(i, j);
                    // Hacky check to not double-count a number where multiple digits are found.
                    if (number != previousNumber) {
                        machineParts.add(number);
                    }
                previousNumber = number;
                }
            }
        }
        return machineParts;
    }

    /**
     * Returns an entire number given the location of a constituent digit's location
     * @param row Row of known digit
     * @param column Column of known digit
     * @return Entire number containing the known digit as an int.
     */
    private static int getNumber(int row, int column) {
        int curCol = column;
        StringBuilder number = new StringBuilder();

        while (curCol > 0 && Character.isDigit(input.get(row).get(curCol - 1))) {
            curCol--;
        }

        while (curCol < input.getFirst().size() && Character.isDigit(input.get(row).get(curCol))) {
            number.append(input.get(row).get(curCol));
            curCol++;
        }
        return Integer.parseInt(number.toString());
    }

    /**
     * Checks if a character is considered a symbol on the schematic.
     * Symbols are non-digit, non-period characters.
     * @param c Character to be tested.
     * @return True if a valid character, false otherwise.
     */
    private static boolean isSymbol(Character c) {
        return (!c.equals('.') && !Character.isDigit(c));
    }

}
