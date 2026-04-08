package edu.kis.powp.jobs2d.drivers.logger;

import edu.kis.powp.jobs2d.Job2dDriver;
import java.util.logging.Logger;

public class TrackingLoggerDriver implements Job2dDriver {
    // Używamy tego samego globalnego loggera, żeby zachować kompatybilność z konfiguracją aplikacji
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private int operationCount = 0;

    @Override
    public void setPosition(int x, int y) {
        this.logger.info("Position set to x: " + x + ", y: " + y + " [" + operationCount + "]");
    }

    @Override
    public void operateTo(int x, int y) {
        operationCount++;
        this.logger.info("Operate to x: " + x + ", y: " + y + " [" + operationCount + "]");
    }

    @Override
    public String toString() {
        return "Tracking Logger Driver";
    }
}