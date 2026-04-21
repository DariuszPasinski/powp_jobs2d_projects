package edu.kis.powp.jobs2d.drivers.visitor;

import edu.kis.powp.jobs2d.drivers.packet_composite.CompositeDriver;

public interface DriverVisitor {
    public void visit(CompositeDriver driver);
}
