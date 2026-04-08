package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.EnsureRecordingDriverIsCurrent;
import edu.kis.powp.jobs2d.drivers.RecordingDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;

public class RecordingFeature {

    private static RecordingDriver recordingDriver;

    public static void setup(DriverManager driverManager) {
        if (recordingDriver != null) {
            return;
        }

        recordingDriver = new RecordingDriver(driverManager.getCurrentDriver());

        driverManager.getChangePublisher()
                .addSubscriber(new EnsureRecordingDriverIsCurrent(driverManager, recordingDriver));

        driverManager.setCurrentDriver(recordingDriver);

    }

    public static RecordingDriver getRecordingDriver() {
        return recordingDriver;
    }
}