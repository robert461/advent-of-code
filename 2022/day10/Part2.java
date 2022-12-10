package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        final String[] input_lines =
            Files.readString(Path.of("day10\\real_input.txt")).split("\n");
    
        LinkedList<Integer> queue = new LinkedList<>();
        Integer xRegister = 1;
        Integer cpuCounter = 0;
        Integer crtCounter = 0;
        Boolean hasFinishedExecuting = true;

        while (cpuCounter <= input_lines.length - 1) {
            if (queue.size() == 0) {
                // begin executing
                String[] line = input_lines[cpuCounter].split(" ");
                String command = line[0].strip();

                if (command.equals("addx"))
                    queue.add(Integer.valueOf(line[1].strip()));

                hasFinishedExecuting = false;
            }

            // Line break for CRT
            if (crtCounter == 40) {
                System.out.println();
                crtCounter = 0;
            }

            // CRT draws pixel
            if (crtCounter == xRegister - 1 || crtCounter == xRegister || crtCounter == xRegister + 1)
                System.out.print("#");
            else
                System.out.print(" "); // better visible with blank space instead of dot!

            if (hasFinishedExecuting) {
                // finish executing
                xRegister += queue.pop();
                cpuCounter--;
            } else hasFinishedExecuting = true;

            cpuCounter++;
            crtCounter++;
        }
    }
}
