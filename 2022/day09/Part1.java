package day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {
    static Integer x = 0;
    static Integer y = 0;
    static Set<List<Integer>> positionsVisited = new HashSet<List<Integer>>();
    static List<Integer> neighboordHood = new ArrayList<>();
    static Boolean canAdd = true;
    static Boolean isRangeSet = false;
    
    static void addNewPosition() {
        List<Integer> entry = new ArrayList<Integer>();
        entry.add(x);
        entry.add(y);
        positionsVisited.add(entry);
        isRangeSet = false;
    }

    static void setNeighborhoodRange(char direction) {
        // Moore neighborhood
        neighboordHood.clear();

        int a = x - 1;
        int b = x + 1;
        int c = y - 1;
        int d = y + 1;

        if (direction == 'U') {
            c--;
            d--;
        } else if (direction == 'D') {
            c++;
            d++;
        } else if (direction == 'R') {
            a--;
            b--;
        } else if (direction == 'L') {
            a++;
            b++;
        }
        
        neighboordHood.add(0, a);
        neighboordHood.add(1, b);
        neighboordHood.add(2, c);
        neighboordHood.add(3, d);
    }

    public static void main(String[] args) throws IOException {
        final String[] input_lines =
            Files.readString(Path.of("day09\\real_input.txt")).split("\n");

        char previousDirection = Character.MIN_VALUE; // Detect direction changes

        addNewPosition(); // Add initial (0, 0) starting position

        for (String line : input_lines) {
            String[] motionInstruction = line.split(" ");
            final char currentDirection = motionInstruction[0].charAt(0);
            final int amount = Integer.valueOf(motionInstruction[1].strip());

            for (int i = 0; i < amount; i++) {
                // Detect head direction change and block tail from following
                if (previousDirection != currentDirection) {
                    if (canAdd) {
                        canAdd = false;

                        if (!isRangeSet) {
                            setNeighborhoodRange(previousDirection);
                            isRangeSet = true;
                        }
                    }

                    previousDirection = currentDirection;
                }

                if (canAdd) addNewPosition();

                // Update head position
                if (currentDirection == 'U') y++;
                else if (currentDirection == 'R') x++;
                else if (currentDirection == 'D') y--;
                else if (currentDirection == 'L') x--;

                // Check where head is moving
                if (!canAdd) {
                    Boolean headOutsideNeighborhood = true;

                    if (x < neighboordHood.get(0)) x++;
                    else if (x > neighboordHood.get(1)) x--;
                    else if (y < neighboordHood.get(2)) y++;
                    else if (y > neighboordHood.get(3)) y--;
                    else headOutsideNeighborhood = false;

                    if (headOutsideNeighborhood) {
                        i--;
                        canAdd = true;
                    }
                }
            }
        }

        System.out.println("Solution 1: " + (positionsVisited.size()));
    }
}
