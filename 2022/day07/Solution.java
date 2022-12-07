package day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Solution {
    static final Integer limit = 100000;
    final static Integer neededSpace = 30000000;
    final static Integer availableSpace = 70000000;
    static Integer currentlyUsedSpace = 0;
    static Integer smallestNeededSpace = Integer.MAX_VALUE;

    static DirectoryNode createStructure(String[] commands) {
        DirectoryNode root = new DirectoryNode("/");
        DirectoryNode current = root;

        for (String command : commands) {
            String[] parameters = command.split("\\s+");

            if (command.startsWith("dir") || command.endsWith("/")) continue;

            if (parameters[0].equals("$")) {
                if (parameters[1].equals("cd")) {
                    if (parameters[2].equals("..")) {
                        current = current.parent;
                    } else {
                        // cd x
                        DirectoryNode child = new DirectoryNode(parameters[2]);
                        current.children.add(child);
                        child.parent = current;
                        current = child;
                    }
                }
            } else {
                // 123 abc
                current.value += Integer.parseInt(parameters[0]);
            }
        }

        currentlyUsedSpace = availableSpace - getDirectorySizeRecursive(root);

        return root;
    }

    static Integer getSumOfTotalSizesUnderLimit(DirectoryNode node) {
        Integer sum = 0;

        // Iterate through all children directories and add their values recursively
        for (DirectoryNode child : node.children) sum += getSumOfTotalSizesUnderLimit(child);

        // Sum file sizes of current directory
        Integer currentDirectorySize = getDirectorySizeRecursive(node);

        if (currentDirectorySize < limit) sum += currentDirectorySize;
        
        return sum;
    }

    static Integer getDirectorySizeRecursive(DirectoryNode node) {
        Integer directorySize = 0;

        for (DirectoryNode child : node.children) directorySize += getDirectorySizeRecursive(child);
        
        directorySize += node.value;

        return directorySize;
    }

    static void setSmallestNeededDirectorySpace(DirectoryNode node) {
        // Check all directory sizes and find the smallest suitable to free up needed space
        for (DirectoryNode child : node.children) setSmallestNeededDirectorySpace(child);

        Integer currentDirectorySize = getDirectorySizeRecursive(node);

        if ((currentlyUsedSpace + currentDirectorySize) > neededSpace)
            smallestNeededSpace = Math.min(smallestNeededSpace, currentDirectorySize);
    }

    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();

        final String[] input_lines = Files.readString(Path.of("day07\\real_input.txt")).split("\n");
        DirectoryNode root = createStructure(input_lines);

        System.out.println("Solution 1: " + getSumOfTotalSizesUnderLimit(root));

        setSmallestNeededDirectorySpace(root);
        System.out.println("Solution 2: " + smallestNeededSpace);

        System.out.println("Program executed in " + (System.currentTimeMillis() - startTime) + "ms");
		
    }
}
