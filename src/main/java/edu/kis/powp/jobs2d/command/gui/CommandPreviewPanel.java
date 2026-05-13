package edu.kis.powp.jobs2d.command.gui;

import javax.swing.JPanel;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.command.manager.CommandPreviewChangeObserver;
import edu.kis.powp.jobs2d.drivers.visitor.VisitableDriver;

public class CommandPreviewPanel extends JPanel{
    CommandPreviewChangeObserver commandPreviewChangeObserver;
    DrawPanelController drawPanelController;
    VisitableDriver driver;

    public CommandPreviewPanel(DrawPanelController drawPanelController, VisitableDriver previewDriver, CommandManager commandManager) {
        this.drawPanelController = drawPanelController;
        this.driver = previewDriver;
        commandPreviewChangeObserver = new CommandPreviewChangeObserver(drawPanelController,driver,commandManager);
        drawPanelController.initialize(this);
    }
}