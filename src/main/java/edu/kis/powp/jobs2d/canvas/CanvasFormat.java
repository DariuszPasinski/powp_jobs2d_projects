package edu.kis.powp.jobs2d.canvas;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.SimpleComplexCommandBuilder;

import java.awt.Shape;
import java.awt.geom.PathIterator;

public interface CanvasFormat extends ICanvas {
    Shape getShape();

    default ICompoundCommand toCommand() {
        SimpleComplexCommandBuilder pathBuilder = new SimpleComplexCommandBuilder();

        PathIterator segments = getShape().getPathIterator(null);

        double[] coordinates = new double[2];

        int startX = 0;
        int startY = 0;

        while (!segments.isDone()) {
            int segmentType = segments.currentSegment(coordinates);

            int destinationX = (int) coordinates[0];
            int destinationY = (int) coordinates[1];

            if (segmentType == PathIterator.SEG_MOVETO) {
                startX = destinationX;
                startY = destinationY;
                pathBuilder.setPosition(destinationX, destinationY);
            } else if (segmentType == PathIterator.SEG_LINETO) {
                pathBuilder.operateTo(destinationX, destinationY);
            } else {
                pathBuilder.operateTo(startX, startY);
            }

            segments.next();
        }

        return pathBuilder.build();
    }
}
