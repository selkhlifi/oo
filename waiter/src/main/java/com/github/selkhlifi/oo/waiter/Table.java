package com.github.selkhlifi.oo.waiter;

import java.util.*;
import java.util.stream.Collectors;

class Table {

    private final int id;
    private final int sizeOfTable;
    private final List<Command> commands = new ArrayList<>();

    public Table(int sizeOfTable, final int id) {
        this.sizeOfTable = sizeOfTable;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void add(Command command) {
        command.addInto(commands);
    }

    int missedCommandsNbr() {
        return sizeOfTable - commands.size();
    }

    public String createOrder() {
        return new Order(this).create();
    }


    private static class Order {
        private final Table table;

        Order(Table table) {
            this.table = table;
        }

        String create() {

            List<OrderRestriction> orderCreationRestrictions = Arrays.asList(new MissingTableRestriction(table), new MissingDishesRestriction(table));

            Optional<EvaluationFailure> evaluationFailure = orderCreationRestrictions.stream()
                    .map(OrderRestriction::check)
                    .filter(f -> f.isPresent())
                    .findFirst()
                    .flatMap(f -> f);

            if(evaluationFailure.isPresent()) {
                return evaluationFailure.get().toString();
            }

            return table.commands.stream()
                            .map(Command::toString)
                            .collect(Collectors.joining(", "));
        }
    }

    private static abstract class OrderRestriction {
        protected Table table;

        OrderRestriction(Table table ) {
            this.table = table;
        }
        abstract Optional<EvaluationFailure> check();

    }

    private static class MissingTableRestriction extends OrderRestriction {

        MissingTableRestriction(Table table) {
            super(table);
        }

        @Override
        public Optional<EvaluationFailure> check() {
            int missedCommandsNbr = table.missedCommandsNbr();
            if(missedCommandsNbr > 0) {
                return Optional.of(new EvaluationFailure("MISSING " + missedCommandsNbr));
            }
            return Optional.empty();
        }
    }

    static class MissingDishesRestriction extends OrderRestriction {

        private String missingDishesOrder = "";

        MissingDishesRestriction(Table table) {
            super(table);
        }

        @Override
        Optional<EvaluationFailure> check() {

            // TODO clean this crappy code
            table.commands.stream()
                    .filter(Command::isMultiple)
                    .collect(Collectors.groupingBy(Command::getDish))
                    .forEach((k, v) -> {//TODO we should break the loop in the first match
                        int missingDishes = Integer.parseInt(k.split(" for ")[1]) - v.size();
                        if (missingDishes > 0) {
                            missingDishesOrder = "MISSING " + missingDishes + " for " + v.get(0);
                        }
                    });

            if (missingDishesOrder.equals("")) {// TODO what does the fucking "" mean ? this is not clean
                return Optional.empty();
            }

            return Optional.of(new EvaluationFailure(missingDishesOrder));
        }
    }

    static class EvaluationFailure {
        private final String failureMsg;

        EvaluationFailure(String failureMsg) {
            this.failureMsg = failureMsg;
        }

        @Override
        public String toString() {
            return failureMsg;
        }
    }

}
