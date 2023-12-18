package day4;

import utils.InputReader;

import java.util.Arrays;
import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./src/main/java/day4/input");
        List<String> input = inputReader.toLines();

        System.out.printf("Part 1: %d %n", totalPointsOfCards(input));
    }

    private static int totalPointsOfCards(List<String> input) {
        int totalPoints = 0;
        for (String line : input) {
            String[] dividedLine = divideCard(line);
            int[] winningNumbers = getNumbers(dividedLine[1]);
            int[] compareNumbers = getNumbers(dividedLine[2]);
            int numberOfMatches = numberOfMatches(winningNumbers, compareNumbers);

            if (numberOfMatches != 0) {
                totalPoints += calculatePoints(numberOfMatches);
            }
        }
        return totalPoints;
    }

    /**
     * Calculates the points for a card where one match is worth 1 point,
     * and each subsequent match doubles the value.
     * @param wins Number of wins on a card as int.
     * @return Score of a card as int.
     */
    private static int calculatePoints(int wins) {
        return (int) Math.pow(2, wins - 1);
    }

    /**
     * Counts the number of matching numbers in two arrays.
     * @param winningNumbers Array of numbers as int array.
     * @param compareNumbers Array of numbers as int array.
     * @return Count of matches present in both arrays as an int.
     */
    private static int numberOfMatches(int[] winningNumbers, int[] compareNumbers) {
        int matches = 0;
        for (int wn : winningNumbers) {
            for (int cn : compareNumbers) {
                if (wn == cn) matches++;
            }
        }
        return matches;
    }

    /**
     * Returns a string of numbers separated by spaces as an int array.
     * @param numbers A single string of numbers separated by spaces.
     * @return An int array of numbers from the input string.
     */
    private static int[] getNumbers(String numbers) {
        return Arrays.stream(numbers.split(" "))
                .filter(str -> null != str && !str.trim().isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    /**
     * Split's a card into its components. Card number, winning numbers, compare numbers.
     * @param card A String of the card with all values.
     * @return All components of the input String as a String array.
     */
    private static String[] divideCard(String card) {
        return Arrays.stream(card.split("[:|]"))
                .map(String::trim)
                .toArray(String[]::new);
    }
}
