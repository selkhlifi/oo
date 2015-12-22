package com.github.selkhlifi.oo.waiter;


import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private final List<Table> tables = new ArrayList<>();

    public int initTable(int sizeOfTable) {
        Table table = new Table(sizeOfTable, tables.size() + 1, new ArrayList<>());
        tables.add(table);
        return table.getId();
    }

    public void customerSays(int tableId, String message) {
        Command command = Command.create(message);
        getTable(tableId).add(command);
    }


    public String createOrder(int tableId) {
        Table table = getTable(tableId);
        return table.createOrder();
    }

    private Table getTable(int tableId) {
        return tables.stream()
                .filter(t -> t.getId() == tableId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("tableId doesn't exist!"));
    }

}
