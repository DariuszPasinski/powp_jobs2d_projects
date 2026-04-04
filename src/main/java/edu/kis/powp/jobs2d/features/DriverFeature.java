package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListener;
import edu.kis.powp.jobs2d.drivers.RecordingDriver;

public class DriverFeature {

    private static DriverManager driverManager = new DriverManager();
    private static Application app;

    private static final RecordingDriver recordingDriver = new RecordingDriver(driverManager.getCurrentDriver());

    public static DriverManager getDriverManager() {
        return driverManager;
    }

    public static RecordingDriver getRecordingDriver() {
        return recordingDriver;
    }

    /**
     * Setup jobs2d drivers Plugin and add to application.
     *
     * @param application Application context.
     */
    public static void setupDriverPlugin(Application application) {
        app = application;
        app.addComponentMenu(DriverFeature.class, "Drivers");
        driverManager.setCurrentDriver(recordingDriver);
    }

    /**
     * Add driver to context, create button in driver menu.
     *
     * @param name   Button name.
     * @param driver Job2dDriver object.
     */
    public static void addDriver(String name, Job2dDriver driver) {
        SelectDriverMenuOptionListener listener = new SelectDriverMenuOptionListener(driver, driverManager);
        app.addComponentMenuElement(DriverFeature.class, name, listener);
    }

    public static void setCurrentDriver(Job2dDriver driver) {
        recordingDriver.setTarget(driver);
        driverManager.setCurrentDriver(recordingDriver);
        updateDriverInfo();
    }

    /**
     * Update driver info.
     * Shows actual target driver name and recording status "[REC]" / "[NO REC]".
     */
    public static void updateDriverInfo() {
        RecordingDriver recDriver = recordingDriver;
        Job2dDriver target = recDriver.getTarget();
        String name = (target == null) ? "No driver" : target.toString();
        String status = recDriver.isRecordingEnabled() ? " [REC]" : " [NO REC]";
        app.updateInfo(name + status);
    }

}
