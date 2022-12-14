package day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Solution {
    // Inspired by: https://github.com/joshuaruegge/adventofcode/blob/main/advent/aoc2022/Day12.java
    public static int modifiedDijkstra(Coordinate2D startCoords, Coordinate2D goalCoords, Node[][] heightmap) {
        var queue = new LinkedList<Coordinate2D>();
        var parents = new HashMap<Coordinate2D, Coordinate2D>();
        var costs = new HashMap<Coordinate2D, Integer>();

        queue.add(startCoords);
        costs.put(startCoords, 0);
        
        while (queue.size() > 0) {
            Coordinate2D current = queue.poll();

            if (current.equals(goalCoords)) return costs.get(goalCoords);

            var neighbors = new ArrayList<Coordinate2D>();
            if (current.x > 0) neighbors.add(new Coordinate2D(current.x - 1, current.y));
            if (current.x < heightmap.length - 1) neighbors.add(new Coordinate2D(current.x + 1, current.y));
            if (current.y > 0) neighbors.add(new Coordinate2D(current.x, current.y - 1));
            if (current.y < heightmap[0].length - 1) neighbors.add(new Coordinate2D(current.x, current.y + 1));
            
            for (Coordinate2D coords : neighbors) {
                var nextChar = heightmap[coords.x][coords.y].height;
                int currentCost = costs.get(current) + 1;

                if (currentCost < costs.getOrDefault(coords, Integer.MAX_VALUE) && nextChar <= heightmap[current.x][current.y].height + 1) {
                    costs.putIfAbsent(coords, currentCost);
                    parents.put(coords, current);
                    queue.add(coords);
                }
            }
        }

        return Integer.MAX_VALUE;
    }
    
    public static void main(String[] args) throws IOException {
        final String[] inputLines =
            Files.readString(Path.of("day12\\real_input.txt")).split("\n");
        
        int columns = inputLines[0].length() - 1;
        int rows = inputLines.length;

        var goalCoords = new Coordinate2D();
        var startCoords = new Coordinate2D();

        var heightmap = new Node[rows][columns];
        var startingAs = new ArrayList<Node>();

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                var chr = inputLines[i].charAt(j);

                if (chr == 'E') {
                    goalCoords = new Coordinate2D(i, j);
                    heightmap[i][j] = new Node('z', goalCoords);
                } else if (chr == 'S') {
                    startCoords = new Coordinate2D(i, j);
                    heightmap[i][j] = new Node('a', startCoords);
                } else
                    heightmap[i][j] = new Node((int) chr, new Coordinate2D(i, j));

                if (chr == 'a') startingAs.add(heightmap[i][j]); // Part 2
            }

        System.out.println("Solution 1: " + modifiedDijkstra(startCoords, goalCoords, heightmap));

        // Part 2: Brute force, check path length for all a's
        var pathLengthsFromStartingAs = new ArrayList<Integer>();

        for (Node startingA : startingAs)
            pathLengthsFromStartingAs.add(modifiedDijkstra(startingA.coords, goalCoords, heightmap));
        
        Collections.sort(pathLengthsFromStartingAs);

        System.out.println("Solution 2: " + pathLengthsFromStartingAs.get(0));
    }
}
