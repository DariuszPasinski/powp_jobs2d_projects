package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class CommandsHistoryObserver implements Subscriber {

    public static class HistoryRecord {
        private DriverCommand command;
        private Instant datetime;

        public HistoryRecord(DriverCommand command, Instant datetime) {
            this.command = command;
            this.datetime = datetime;
        }

        public Instant getDatetime() {
            return datetime;
        }

        public DriverCommand getCommand() {
            return command;
        }

        public void setDatetime(Instant datetime) {
            this.datetime = datetime;
        }

        public void setCommand(DriverCommand command) {
            this.command = command;
        }
    }

    private final List<HistoryRecord> history = new ArrayList<>();
    private int maxSize = 10;

    @Override
    public void update() {
        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        if (history.size() >= maxSize) {
            history.remove(0);
        }
        history.add(new HistoryRecord(command, Instant.now()));
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        while (history.size() >= maxSize) {
            history.remove(0);
        }
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public List<HistoryRecord> getHistory() {
        return history;
    }
}
