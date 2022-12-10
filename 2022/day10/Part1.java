package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class Part1 {
    public static void main(String[] args) throws IOException {
        final String[] input_lines =
            Files.readString(Path.of("day10\\real_input.txt")).split("\n");
        
        LinkedList<Integer> queue = new LinkedList<>();
        Integer xRegister = 1;
        Integer sumInterestingSignalStrength = 0;
        Integer cycle = 1;
        Integer counter = 0;

        while (cycle <= 220) {
            if (cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220)
                sumInterestingSignalStrength += cycle * xRegister;

            if (queue.size() > 0) {
                xRegister += queue.pop();
                counter--;
            } else {
                String[] line = input_lines[counter].split(" ");
                String command = line[0].strip();

                if (command.equals("addx")) queue.add(Integer.valueOf(line[1].strip()));
            }

            cycle++;
            counter++;
        }

        System.out.println("Solution 1: " + sumInterestingSignalStrength);
    }
}
