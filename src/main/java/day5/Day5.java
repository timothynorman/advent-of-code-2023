package day5;

import utils.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Day5 {

//    private static HashMap<String, List<long[]>> almanac = new HashMap<>();

    private static List<List<long[]>> almanac = new ArrayList<>();

    private static List<long[]> seedToSoil = new ArrayList<>();
    private static List<long[]> soilToFertilizer = new ArrayList<>();
    private static List<long[]> fertilizerToWater = new ArrayList<>();
    private static List<long[]> waterToLight = new ArrayList<>();
    private static List<long[]> lightToTemperature = new ArrayList<>();
    private static List<long[]> temperatureToHumidity = new ArrayList<>();
    private static List<long[]> humidityToLocation = new ArrayList<>();

    private static long[] seeds;

    private static String[] almanacMaps = {
            "seedToSoil",
            "soilToFertilizer",
            "fertilizerToWater",
            "waterToLight",
            "lightToTemperature",
            "temperatureToHumidity",
            "humidityToLocation"};
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./src/main/java/day5/input");
        List<String> input = inputReader.toLines();

        System.out.printf("Part 1: %d %n", findLowestLocation(input));
        System.out.printf("Part 2: %d %n", findLowestLocationFromRange(input));
    }

    /**
     * Answer for Part 1: Find lowest location base on starting seeds.
     * @param input List of String inputs
     * @return The lowest value of location as a Long.
     */
    private static long findLowestLocation(List<String> input) {
        processInput(input);
        ArrayList<Long> seedToLocations = new ArrayList<>();

        for (long seed : seeds) {
            seedToLocations.add(seedToLocationTransformation(seed));
        }
        return Collections.min(seedToLocations);
    }

    /**
     * Answer for Part 2: Find lowest location based on a range of starting seeds.
     * @param input List of String inputs.
     * @return The lowest value of locations as a Long.
     */
    private static Long findLowestLocationFromRange(List<String> input) {
        processInput(input);
        ArrayList<Long> seedRangesToLocations = new ArrayList<>();

        for (int i = 0; i < seeds.length - 1; i += 2) {
            seedRangesToLocations.add(findLowestLocationOfOnePair(seeds[i], seeds[i + 1]));
        }

        return Collections.min(seedRangesToLocations);
    }

    /**
     * Finds the lowest location given a pair of values representing a starting seed and range of following seeds.
     * @param startingNumber First seed in the range as a Long.
     * @param range Number of seeds in the range as a Long.
     * @return The lowest location value from the starting seed range.
     */
    private static Long findLowestLocationOfOnePair(Long startingNumber, Long range) {
        Long lowestLocationOfOnePair = null;

        for (Long i = 0L; i <= range; i++) {
            Long location = seedToLocationTransformation(startingNumber + i);
            if (lowestLocationOfOnePair == null) lowestLocationOfOnePair = location;
            else if (location < lowestLocationOfOnePair) lowestLocationOfOnePair = location;
        }

        return lowestLocationOfOnePair;
    }

    /**
     * Populates list based on the problem in put.
     * @param input Starting input.
     */
    private static void processInput (List<String> input) {
        List<long[]> mapPointer = null;
        for (String line : input)
            if (!line.isEmpty()) {
                {
                    if (line.contains("seeds:")) {
                        seeds = Arrays.stream(line.split("[: ]"))
                                .filter(c -> c.matches("\\d+"))
                                .mapToLong(Long::parseLong)
                                .toArray();
                    }
                    else if (line.contains("seed-to-soil")) mapPointer = seedToSoil;
                    else if (line.contains("soil-to-fertilizer")) mapPointer = soilToFertilizer;
                    else if (line.contains("fertilizer-to-water")) mapPointer = fertilizerToWater;
                    else if (line.contains("water-to-light")) mapPointer = waterToLight;
                    else if (line.contains("light-to-temperature")) mapPointer = lightToTemperature;
                    else if (line.contains("temperature-to-humidity")) mapPointer = temperatureToHumidity;
                    else if (line.contains("humidity-to-location")) mapPointer = humidityToLocation;
                    else {
                        long[] nextMap = Arrays.stream(line.split(" "))
                                .mapToLong(Long::parseLong)
                                .toArray();
                        mapPointer.add(nextMap);
                    }
                }
            }
    }

    /**
     * Processes a value from seed to location though all intermediate maps.
     * @param input Starting seed value as a Long.
     * @return End location value as a Long.
     */
    private static long seedToLocationTransformation(long input) {
       return almanacMap(
               almanacMap(
                       almanacMap(
                               almanacMap(
                                       almanacMap(
                                               almanacMap(
                                                       almanacMap(input, seedToSoil), soilToFertilizer), fertilizerToWater), waterToLight), lightToTemperature), temperatureToHumidity), humidityToLocation);
    }

    /**
     * Logic for mapping a value based on rules of this problem.
     * @param input Starting value to be mapped as a Long.
     * @param almanacMap List of mapping rules depending on value to be mapped.
     * @return End value after being mapped as a Long.
     */
    private static long almanacMap (long input, List<long[]> almanacMap) {
        for (long[] map : almanacMap) {
            if (input >= map[1] && input < (map[1] + map[2])) {
                return input + (map[0] - map[1]);
            }
        }
        return input;
    }
}
