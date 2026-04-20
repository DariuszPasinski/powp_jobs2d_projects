package edu.kis.powp.jobs2d.canvas;

import edu.kis.powp.jobs2d.command.ICompoundCommand;

public abstract class ShapeBasedCanvas implements ICanvas {
    protected abstract ICompoundCommand buildCommand();

    @Override
    public ICompoundCommand toCommand() {
        return buildCommand();
    }
}
