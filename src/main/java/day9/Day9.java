package day9;

import utils.InputReader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day9 {

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./src/main/java/day9/input");
        List<List<Integer>> input = inputReader.toLinesInteger();

        System.out.println(partOne(input));

//        System.out.println(findNextInteger(input.get(123)));
    }

    /**
     * Performs logic for Part 1 of the question.
     * Finds the next value for each line of integers and returns the sum
     * of all of these next values.
     * @param input A list of lists of integers.
     * @return The sum of next values from each line.
     */
    private static Long partOne(List<List<Integer>> input) {
        return input.stream()
                .mapToLong(line -> findNextInteger(line))
                .sum();
    }

    private static Integer findNextInteger(List<Integer> input) {
        Integer lastInteger = input.getLast();
        List<Integer> differences = findDifferences(input);
        boolean allZeros = differences.stream().allMatch(i -> i == 0);

        if (allZeros) {
            return lastInteger;
        }
        else {
            Integer nextInteger = lastInteger + findNextInteger(differences);
            return nextInteger;
        }
    }

    /**
     * Find the difference between each element in a list.
     * @param input A list of integers
     * @return The difference between input integers as integers.
     */
    private static List<Integer> findDifferences(List<Integer> input) {
        return IntStream.range(0, input.size()-1)
                .mapToObj(i -> input.get(i+1) - input.get(i))
                .collect(Collectors.toList());
    }
}


