package day6;

import utils.InputReader;

import java.util.Arrays;
import java.util.List;

public class Day6 {

    public static void main(String[] args) {

        InputReader inputReader = new InputReader("./src/main/java/day6/input");
        List<String> input = inputReader.toLines();

        System.out.printf("Part 1: %d %n", productOfNumberOfWinningStrategies(input));
        System.out.printf("Part 2: %d %n", winningCombinationsFixedKerning(input));
    }

    /**
     * Calculates the product of all counts of possible winning strategies for an array of races for part 1.
     * @param input List of Strings with the time and distances of the races.
     * @return The product of possible combinations to beat the existing race records.
     */
    private static int productOfNumberOfWinningStrategies(List<String> input) {
        int productOfWinningStrategies = 1;
        int[] time = stringToIntArray(input.get(0));
        int[] dist = stringToIntArray(input.get(1));

        for (int i = 0; i < time.length; i++) {
            productOfWinningStrategies *= (int) numberOfWinningStrategies(time[i], dist[i]);
        }
        return productOfWinningStrategies;
    }

    /**
     * Calculates the number of possible winning conditions for the single long race for part 2.
     * @param input List of Strings with the time and distance of the race.
     * @return The count of possible combinations to beat the existing race record.
     */
    private static long winningCombinationsFixedKerning(List<String> input) {
        long time = fixKerning(stringToIntArray(input.get(0)));
        long dist = fixKerning(stringToIntArray(input.get(1)));
        return numberOfWinningStrategies(time, dist);
    }

    /**
     * Counts the possible winning strategies for the race where you may wait to start to increase your speed by one
     * unit per unit of time.
     * @param time Total allowed time for the race.
     * @param targetDistance Current best distance to beat to count as a win.
     * @return Count of possible winning strategies.
     */
    private static long numberOfWinningStrategies(long time, long targetDistance) {
        long numberOfWinningStrategies = 0L;
        for (int i = 1; i < time; i++) {
            long distance = i * (time - i);
            if (distance > targetDistance) numberOfWinningStrategies++;
        }
        return numberOfWinningStrategies;
    }

    /**
     * Takes an input string and extracts race details into an int[].
     * @param input String of input details.
     * @return int[] of race details.
     */
    private static int[] stringToIntArray(String input) {
        return Arrays.stream(input.split("[: ]"))
                .filter(str -> null != str && !str.trim().isEmpty())
                .filter(str -> str.matches("[0-9]+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    /**
     * Repairs gaps in input string resulting from bad kerning.
     * @param input List of race details which have been split based on input spacing.
     * @return A single digit of all input race details with the gaps removed.
     */
    private static long fixKerning(int[] input) {
        StringBuilder fixedOutput = new StringBuilder();
        for (int i : input) {
            fixedOutput.append(i);
        }
        return Long.parseLong(fixedOutput.toString());
    }
}
