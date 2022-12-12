package day11;

import java.util.List;

public class Monkey {
    public List<Long> items;
    public int operationTypeId ;
    public int operationValue;
    public long divideValue;
    public int monkeyIdTrue;
    public int monkeyIdFalse;

    public Monkey(List<Long> items,
        int operationTypeId,
        int operationValue,
        long divideValue,
        int monkeyIdTrue,
        int monkeyIdFalse) {

        this.items = items;
        this.operationTypeId = operationTypeId;
        this.operationValue = operationValue;
        this.divideValue = divideValue;
        this.monkeyIdTrue = monkeyIdTrue;
        this.monkeyIdFalse = monkeyIdFalse;
    }
}
