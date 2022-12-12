package day11;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    final static int ADD = 0;
    final static int MULTIPLY = 2;
    final static int OLD = -1;
    static int lcm;

    public static ArrayList<Monkey> getMonkeys() throws IOException {
        final String[] inputLines =
            Files.readString(Path.of("day11\\real_input.txt")).split("\r\n\r\n");
        
        var monkeys = new ArrayList<Monkey>();
        
        for (String monkeyLines : inputLines) {
            String[] monkeyStats = monkeyLines.split("\n");
            
            String[] formattedItems = monkeyStats[1].split(":");
            List<Long> items = Stream.of(formattedItems[1].split(","))
                .mapToLong(e -> Long.parseLong(e.strip()))
                .boxed()
                .collect(Collectors.toList());

            char operationType = monkeyStats[2].charAt(23);
            int operationTypeId = ADD;
            if (operationType == '+') operationTypeId = ADD;
            else if (operationType == '*') operationTypeId = MULTIPLY;

            var operationValueInitial = monkeyStats[2].substring(25, monkeyStats[2].length()).strip();
            
            int operationValue;
            if (operationValueInitial.equals("old")) operationValue = OLD;
            else operationValue = Integer.parseInt(operationValueInitial);

            long divideValue = Long.parseLong(monkeyStats[3].substring(21, monkeyStats[3].length()).strip());

            int monkeyIdTrue = Integer.parseInt(monkeyStats[4].substring(29,  monkeyStats[4].length()).strip());
            int monkeyIdFalse = Integer.parseInt(monkeyStats[5].substring(30,  monkeyStats[5].length()).strip());

            monkeys.add(new Monkey(items, operationTypeId, operationValue, divideValue, monkeyIdTrue, monkeyIdFalse));
        }

        lcm = 1;
        for (Monkey monkey : monkeys)
            lcm *= monkey.divideValue;

        return monkeys;
    }

    public static void main(String[] args) throws IOException {
        final var limits = new int[]{20, 10000};

        for (var limit : limits) {
            ArrayList<Monkey> monkeys = getMonkeys();
            var numberOfMonkeys = monkeys.size();
            var inspectedValues = new int[numberOfMonkeys];

            for (int i = 0; i < limit; i++) {
                for (int j = 0; j < numberOfMonkeys; j++) {
                    var currentMonkey = monkeys.get(j);

                    while (currentMonkey.items.size() > 0) {
                        Long worryLevel = currentMonkey.items.remove(0);
                        
                        inspectedValues[j]++; // Monkey inspects item

                        if (currentMonkey.operationValue == OLD) worryLevel *= worryLevel;
                        else if (currentMonkey.operationTypeId == ADD) worryLevel += currentMonkey.operationValue;
                        else if (currentMonkey.operationTypeId == MULTIPLY) worryLevel *= currentMonkey.operationValue;

                        if (limit == 20) worryLevel = (long) Math.floor(worryLevel / 3);
                        else worryLevel %= lcm;
                        
                        if ((worryLevel % currentMonkey.divideValue) == 0)
                            monkeys.get(currentMonkey.monkeyIdTrue).items.add(worryLevel);
                        else
                            monkeys.get(currentMonkey.monkeyIdFalse).items.add(worryLevel);
                    }
                }
            }

            Arrays.sort(inspectedValues);

            System.out.println("Solution for limit " + limit + ": " + BigInteger.valueOf(inspectedValues[inspectedValues.length - 1]).multiply(BigInteger.valueOf(inspectedValues[inspectedValues.length - 2])));
        }
    }
}
