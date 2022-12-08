package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Solution {
    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();

        final String[] input_lines = Files.readString(Path.of("day08\\real_input.txt")).split("\n");

        Integer size = input_lines[0].length() - 1;

        Integer[][] heightMap = new Integer[size][size];

        Integer amountOfEdges = 2 * size + 2 * (size - 2);
        Integer visibleTrees = amountOfEdges;
        Integer highestScenicScore = 0;

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                heightMap[i][j] = Integer.parseInt(String.valueOf(input_lines[i].charAt(j)));

        // Part 1
        for (int i = 1; i < size - 1; i++) {
            inner:
            for (int j = 1; j < size - 1; j++) {
                Integer current = heightMap[i][j];
                Boolean visible = true;

                // Top
                for (int k = i; k > 0; k--)
                    if (current <= heightMap[k - 1][j]) {
                        visible = false;
                        break;
                    }

                if (visible) {
                    visibleTrees++;
                    continue inner;
                } else visible = true;

                
                // Bottom
                for (int k = i; k < size - 1; k++)
                    if (current <= heightMap[k + 1][j]) {
                        visible = false;
                        break;
                    }

                if (visible) {
                    visibleTrees++;
                    continue inner;
                } else visible = true;

                // Right
                for (int k = j; k < size - 1; k++)
                    if (current <= heightMap[i][k + 1]) {
                        visible = false;
                        break;
                    }

                if (visible) {
                    visibleTrees++;
                    continue inner;
                } else visible = true;

                // Left
                for (int k = j; k > 0; k--)
                    if (current <= heightMap[i][k - 1]) {
                        visible = false;
                        break;
                    }

                if (visible) visibleTrees++;
            }
        }

        // Part 2
        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                Integer current = heightMap[i][j];
                Integer scenicScore = 1;
                Integer viewingDistance = 0;

                // Top
                for (int k = i; k > 0; k--) {
                    viewingDistance++;
                    if (current <= heightMap[k - 1][j]) break;
                }

                scenicScore *= viewingDistance;
                viewingDistance = 0;

                // Bottom
                for (int k = i; k < size - 1; k++) {
                    viewingDistance++;
                    if (current <= heightMap[k + 1][j]) break;
                }

                scenicScore *= viewingDistance;
                viewingDistance = 0;

                // Right
                for (int k = j; k < size - 1; k++) {
                    viewingDistance++;
                    if (current <= heightMap[i][k + 1]) break;
                }

                scenicScore *= viewingDistance;
                viewingDistance = 0;

                // Left
                for (int k = j; k > 0; k--) {
                    viewingDistance++;
                    if (current <= heightMap[i][k - 1]) break;
                }

                scenicScore *= viewingDistance;

                highestScenicScore = Math.max(highestScenicScore, scenicScore);
            }
        }

        System.out.println("Solution 1: " + visibleTrees);
        System.out.println("Solution 2: " + highestScenicScore);

        System.out.println("Program executed in " + (System.currentTimeMillis() - startTime) + "ms");
		
    }
}
