package edu.kis.powp.jobs2d.drivers.visitor;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.packet_composite.CompositeDriver;

public class FullNameGetterVisitor implements DriverVisitor {

    private StringBuilder builder = new StringBuilder("Full name: ");

    @Override
    public void visit(CompositeDriver driver) {
        builder.append(driver.toString()).append(" ");

        for (Job2dDriver d : driver.getDrivers()) {
            if (d instanceof VisitableDriver) {
                ((VisitableDriver) d).accept(this);
            } else {
                builder.append(d.toString()).append(" ");
            }
        }
    }

    public String getFullName() {
        return builder.toString();
    }
}
