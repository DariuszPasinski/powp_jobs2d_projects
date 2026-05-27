package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.history.CommandsHistory;
import edu.kis.powp.jobs2d.command.history.HistoryRecord;

import javax.swing.*;
import java.util.List;

public class CommandsHistoryWindow extends JFrame implements WindowComponent {

    public CommandsHistoryWindow(CommandsHistory commandsHistory) {
        this.setTitle("Commands History");
        this.setSize(400, 600);

        List<HistoryRecord> history = commandsHistory.getHistory();
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}
