package edu.kis.powp.jobs2d.command.gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.command.manager.CommandPreviewChangeObserver;
import edu.kis.powp.jobs2d.drivers.visitor.VisitableDriver;

public class CommandPreviewWindow extends JFrame implements WindowComponent {
    CommandPreviewChangeObserver commandPreview;

    public CommandPreviewWindow(CommandManager commandManager, DrawPanelController drawPanelController, VisitableDriver previewDriver) {
        this.setTitle("Command Preview");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        JPanel previewPanel = new JPanel();
        drawPanelController.initialize(previewPanel);
        commandPreview = new CommandPreviewChangeObserver(drawPanelController,previewDriver,commandManager);
        content.add(previewPanel);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

}