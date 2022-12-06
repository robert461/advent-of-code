package day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Solution {

    static Integer findMarkerOf(char[] dataStreamBuffer, Integer numberOfDisctinctCharacters) {

        Integer index = 0;
        Boolean hasNoEqual = false;

        do {

            char[] characters = new char[numberOfDisctinctCharacters];
            for (int n = 0; n < numberOfDisctinctCharacters; n++) characters[n] = dataStreamBuffer[n+index];

            searchLoop:
            for (int i = 0; i <= characters.length; i++) {

                for (int j = i+1; j <= characters.length - 1; j++) {

                    hasNoEqual = (characters[i] != characters[j]);

                    if (!hasNoEqual) break searchLoop;
                }

            }

            index += 1;

        } while (!hasNoEqual && (index < dataStreamBuffer.length - numberOfDisctinctCharacters));

        return (index + numberOfDisctinctCharacters) - 1;
        
    }

    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();

        final char[] input_line = Files.readString(Path.of("day06\\real_input.txt")).toCharArray();

        System.out.println("Solution 1: " + findMarkerOf(input_line, 4));
        System.out.println("Solution 2: " + findMarkerOf(input_line, 14));

        System.out.println("Program executed in " + (System.currentTimeMillis() - startTime) + "ms");
		
    }

}
