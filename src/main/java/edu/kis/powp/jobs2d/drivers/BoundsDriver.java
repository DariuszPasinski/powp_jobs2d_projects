package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.drivers.visitor.DriverVisitor;
import edu.kis.powp.jobs2d.drivers.visitor.VisitableDriver;

import java.awt.*;

public class BoundsDriver implements VisitableDriver {

    private final VisitableDriver innerDriver;
    private final Rectangle bounds;
    private int virtualX, virtualY;
    private int x, y;

    public BoundsDriver(VisitableDriver innerDriver, Rectangle bounds) {
        this.innerDriver = innerDriver;
        this.bounds = bounds;
    }

    @Override
    public void accept(DriverVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void setPosition(int nx, int ny) {
        virtualX = nx;
        virtualY = ny;
        moveTo(clampX(nx), clampY(ny));
    }

    @Override
    public void operateTo(int nx, int ny) {
        int[] clipped = clip(virtualX, virtualY, nx, ny);

        if (clipped != null) {
            moveTo(clipped[0], clipped[1]);
            innerDriver.operateTo(clipped[2], clipped[3]);
            x = clipped[2];
            y = clipped[3];
        }

        virtualX = nx;
        virtualY = ny;
        moveTo(clampX(nx), clampY(ny));
    }

    private int[] clip(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int minX = bounds.x, minY = bounds.y;
        int maxX = bounds.x + bounds.width, maxY = bounds.y + bounds.height;

        double[] p = { -dx, dx, -dy, dy };
        double[] q = { x1 - minX, maxX - x1, y1 - minY, maxY - y1 };

        double tEnter = 0, tExit = 1;

        for (int i = 0; i < 4; i++) {
            if (p[i] == 0) {
                if (q[i] < 0) return null;
            } else {
                double t = q[i] / p[i];
                if (p[i] < 0) {
                    if (t > tEnter) tEnter = t;
                } else {
                    if (t < tExit) tExit = t;
                }
            }
        }

        if (tEnter > tExit) return null;

        return new int[]{
                (int) Math.round(x1 + tEnter * dx),
                (int) Math.round(y1 + tEnter * dy),
                (int) Math.round(x1 + tExit  * dx),
                (int) Math.round(y1 + tExit  * dy)
        };
    }

    private void moveTo(int nx, int ny) {
        if (nx != x || ny != y) {
            innerDriver.setPosition(nx, ny);
            x = nx;
            y = ny;
        }
    }

    private int clampX(int v) {
        return Math.max(bounds.x, Math.min(v, bounds.x + bounds.width));
    }

    private int clampY(int v) {
        return Math.max(bounds.y, Math.min(v, bounds.y + bounds.height));
    }

    public VisitableDriver getInnerDriver() {
        return innerDriver;
    }
}
