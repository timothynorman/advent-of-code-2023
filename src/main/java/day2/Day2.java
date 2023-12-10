package day2;

import utils.InputReader;

import java.util.Arrays;
import java.util.List;

public class Day2 {

    private final static int MAX_RED = 12;
    private final static int MAX_GREEN = 13;
    private final static int MAX_BLUE = 14;

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./src/main/java/day2/input");
        List<String> input = inputReader.toLines();

        // Part 1
        sumOfPossibleGameNumbers(input);

        // Part 2
        sumOfPowers(input);
    }

    private static void sumOfPowers(List<String> input) {
        int total = 0;

        for (String line : input) {
            String[] splitLine = line.split(":");
            String gameDisplays = splitLine[1];
            total += powerOfGames(gameDisplays);
        }

        System.out.printf("Part 2: %d", total);
    }

    private static int powerOfGames(String games) {
        int maxRed = 0;
        int maxGreen = 0;
        int maxBlue = 0;

        for (String game : splitAllGames(games)) {
            String[] splitGame = game.split(" ");
            int number = Integer.parseInt(splitGame[0]);
            String colour = splitGame[1];

            switch (colour) {
                case "red":
                    if (number > maxRed) {maxRed = number;}
                    break;
                case "green":
                    if (number > maxGreen) {maxGreen = number;}
                    break;
                case "blue":
                    if (number > maxBlue) {maxBlue = number;}
                    break;
            }
        }
        return maxRed * maxGreen * maxBlue;
    }

    private static void sumOfPossibleGameNumbers(List<String> input) {
        int total = 0;

        for (String line : input) {
            String[] splitLine = line.split(":");
            String gameTitle = splitLine[0];
            String gameDisplays = splitLine[1];
            if (areAllDisplaysPossible(gameDisplays)) {
                String[] gameNameParts = gameTitle.split(" ");
                int gameNumber = Integer.parseInt(gameNameParts[1]);

                total += gameNumber;
            }
        }

        System.out.printf("Part 1: %d %n", total);
    }

    private static boolean isPossibleDisplay(String display) {
        String[] pull = display.split(" ");
        int number = Integer.parseInt(pull[0]);
        String colour = pull[1];

        switch (colour) {
            case "red":
                if (number <= MAX_RED) {return true;}
                break;
            case "green":
                if (number <= MAX_GREEN) {return true;}
                break;
            case "blue":
                if (number <= MAX_BLUE) {return true;}
                break;
        }
        return false;
    }

    private static boolean areAllDisplaysPossible(String game) {
        for (String display : splitAllGames(game)) {
            if (!isPossibleDisplay(display)) {return false;}
        }
        return true;
    }

    private static String[] splitAllGames(String games) {
        return Arrays.stream(games.split("[;,]"))
                .map(String::trim)
                .toArray(String[]::new);
    }

}
