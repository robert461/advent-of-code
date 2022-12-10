package day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part2 {
    // Note: Solution is not original but inspired by others
    
    static final int numberOfKnots = 10;

    public static void main(String[] args) throws IOException {
        final String[] input_lines =
            Files.readString(Path.of("day09\\real_input.txt")).split("\n");
        
        Set<List<Integer>> positionsVisited = new HashSet<List<Integer>>();

        // Save x and y coordinate values separately
        List<Integer> knotX = new ArrayList<Integer>(numberOfKnots);
        List<Integer> knotY = new ArrayList<Integer>(numberOfKnots);
        
        for (int n = 0; n < numberOfKnots; n++) {
            knotX.add(0);
            knotY.add(0);
        }

        for (String line : input_lines) {
            String[] motionInstruction = line.split(" ");
            final char currentDirection = motionInstruction[0].charAt(0);
            final int amount = Integer.valueOf(motionInstruction[1].strip());

            for (int i = 0; i < amount; i++) {
                // Update rope position
                if (currentDirection == 'U')
                    knotY.set(0, knotY.get(0) + 1);
                else if (currentDirection == 'R')
                    knotX.set(0, knotX.get(0) + 1);
                else if (currentDirection == 'D')
                    knotY.set(0, knotY.get(0) - 1);
                else if (currentDirection == 'L')
                    knotX.set(0, knotX.get(0) - 1);

                for (int j = 1; j < numberOfKnots; j++) {
                    // Check where head moved (...if head is inside rope body)
                    Integer dx = knotX.get(j - 1) - knotX.get(j);
                    Integer dy = knotY.get(j - 1) - knotY.get(j);

                    // Update rope position if head moved far enough
                    if (dx > 1 || dx < -1 || dy > 1 || dy < -1) {
                        knotX.set(j, knotX.get(j) + Integer.signum(dx));
                        knotY.set(j, knotY.get(j) + Integer.signum(dy));
                    }
                }

                List<Integer> entry = new ArrayList<Integer>();
                entry.add(knotX.get(numberOfKnots - 1));
                entry.add(knotY.get(numberOfKnots - 1));
                positionsVisited.add(entry);
            }
        }

        System.out.println("Solution 2: " + (positionsVisited.size()));
    }
}
