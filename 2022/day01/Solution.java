package day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Solution {

	public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();

        final String[] input_lines = Files.readString(Path.of("day01\\real_input.txt")).split("\r\n\r\n");

        List<Integer> elfCalories = new ArrayList<>();

        for (String line : input_lines) {

            int currentSumOfCalories = Stream.of(line.split("\r\n")).mapToInt(Integer::parseInt).sum();

            elfCalories.add(currentSumOfCalories);

        }

        Collections.sort(elfCalories, Collections.reverseOrder());

        System.out.println("Solution 1: " + elfCalories.get(0));

        System.out.println("Solution 2: " + elfCalories.stream().limit(3).mapToInt(Integer::intValue).sum());

        System.out.println("Program executed in " + (System.currentTimeMillis() - startTime) + "ms");
		
	}

}
