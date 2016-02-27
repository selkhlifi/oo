package com.github.selkhlifi.oo.waiter;

import java.util.List;
import java.util.Optional;

public class Command {

    private String dish;
    private String client;

    public Command(String client, String dish) {
        this.client = client;
        this.dish = dish;
    }

    public static Command create(String message) {
        String[] splitMessage = message.split(": ");
        String client = splitMessage[0];
        String dish = splitMessage[1];

        if(dish.equals("Same")) {
            return new Same(client);
        }

        return new Command(client, dish);
    }

    public void addInto(List<Command> commands) {
        // if already commanded => find & update
        // else => create & add

        Optional<Command> optionalCommand = commands
                .stream()
                .filter(c -> c.client.equals(Command.this.client))
                .findFirst();

        if(optionalCommand.isPresent()) {
            optionalCommand.get().dish = this.dish;
        } else {
            commands.add(this);
        }
    }

    protected String client() {
        return client;
    }

    boolean isMultiple() {
        return dish.contains(" for ");
    }

    @Override
    public String toString() {
        return dish;
    }

    public String getDish() {
        return dish;
    }



    static class Same extends Command {

        public Same(String client) {
            super(client, "");
        }

        @Override
        public void addInto(List<Command> commands) {
            if(!commands.isEmpty()) {
                Command last = commands.get(commands.size() - 1);
                new Command(client(), last.dish).addInto(commands);
            }
        }
    }
}
