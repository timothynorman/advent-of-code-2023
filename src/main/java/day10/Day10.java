package day10;

import utils.InputReader;

import java.util.ArrayList;

public class Day10 {

    public static ArrayList<ArrayList<Character>> input;
    public static int startingX;
    public static int startingY;
    public static int xLocation;
    public static int yLocation;
    public static ArrayList<Character> path = new ArrayList<>();
    public static String lastDirection;


    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./src/main/java/day10/input");
        input = inputReader.toCharGrid();

        findStartLocation();
        System.out.printf("Starting location at: %d,%d%n", startingX, startingY);

        xLocation = startingX;
        yLocation = startingY;

        followPath();
        System.out.printf("Part 1: Farthest point from start is %d steps.%n", path.size()/2);

    }

    private static void findStartLocation() {
        Character c;
        for (int i = 0; i < input.size(); i++) {            //y-axis
            for (int j = 0; j < input.get(i).size(); j++) { //x-axis
                if ((c = input.get(i).get(j)).equals('S')) {
                    startingX = j;
                    startingY = i;
                }
            }
        }
    }

    private static Character checkLeft() {
        if (xLocation == 0) return 'x';
        return input.get(yLocation).get(xLocation - 1);
    }

    private static Character checkRight() {
        if (xLocation == input.get(0).size()-1) return 'x';
        return input.get(yLocation).get(xLocation + 1);
    }

    private static Character checkUp() {
        if (yLocation == 0) return 'x';
        return input.get(yLocation - 1).get(xLocation);
    }

    private static Character checkDown() {
        if (yLocation == input.size()-1) return 'x';
        return input.get(yLocation + 1).get(xLocation);
    }

    private static Character currentCharacter() {
        return input.get(yLocation).get(xLocation);
    }
    private static void addCurrentCharacterToPath() {
        path.add(currentCharacter());
    }
    private static void moveLeft() {
        xLocation--;
        lastDirection = "right";
        addCurrentCharacterToPath();
    }

    private static boolean isValidLeft() {
        return (checkLeft() == 'F' || checkLeft() == '-' || checkLeft() == 'L' || checkLeft() == 'S') &&
                (currentCharacter().equals('7') || currentCharacter().equals('-') || currentCharacter().equals('J') || currentCharacter().equals('S')) &&
                (lastDirection != "left");
    }

    private static void moveRight() {
        xLocation++;
        lastDirection = "left";
        addCurrentCharacterToPath();
    }

    private static boolean isValidRight() {
        return (checkRight() == '7' || checkRight() == '-' || checkRight() == 'J' || checkRight() == 'S') &&
                (lastDirection != "right") &&
                (currentCharacter().equals('F') || currentCharacter().equals('-') || currentCharacter().equals('L') || currentCharacter().equals('S'));
    }

    private static void moveUp() {
        yLocation--;
        lastDirection = "down";
        addCurrentCharacterToPath();
    }

    private static boolean isValidUp() {
        return (checkUp() == 'F' || checkUp() == '|' || checkUp() == '7' || checkUp() == 'S') &&
                (lastDirection != "up") &&
                (currentCharacter().equals('J') || currentCharacter().equals('|') || currentCharacter().equals('L') || currentCharacter().equals('S'));
    }

    private static void moveDown() {
        yLocation++;
        lastDirection = "up";
        addCurrentCharacterToPath();
    }

    private static boolean isValidDown() {
        return (checkDown() == 'J' || checkDown() == '|' || checkDown() == 'L' || checkDown() == 'S') &&
                (lastDirection != "down") &&
                (currentCharacter().equals('F') || currentCharacter().equals('|') || currentCharacter().equals('7') || currentCharacter().equals('S'));
    }

    private static void followPath() {
        Character c = 'x';
        while (c != 'S') {
            if (isValidLeft()) moveLeft();
            else if (isValidRight()) moveRight();
            else if (isValidUp()) moveUp();
            else if (isValidDown()) moveDown();
            c = currentCharacter();
        }
    }
}
