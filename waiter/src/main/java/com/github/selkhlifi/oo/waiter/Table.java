package com.github.selkhlifi.oo.waiter;

import java.util.List;
import java.util.stream.Collectors;

class Table {
    private final int id;
    private final int sizeOfTable;
    private final List<Command> commands;

    public Table(int sizeOfTable, final int id, List<Command> commands) {
        this.sizeOfTable = sizeOfTable;
        this.id = id;
        this.commands = commands;
    }

    public int getId() {
        return id;
    }

    public void add(Command command) {
        command.addInto(commands);
    }

    public String createOrder() {
        System.out.println("Size of table :" + sizeOfTable);
        System.out.println("Commands :" + commands);
        int missing = sizeOfTable - commands.size();
        if(missing > 0) {
            return "MISSING " + missing;
        }
        return commands.stream()
                .map(Command::toString)
                .collect(Collectors.joining(", "));
    }
}
