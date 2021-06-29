package utils.reports;

import com.aventstack.extentreports.Status;

public enum LogStatus {

    INFO(Status.INFO),
    WARNING(Status.WARNING),
    SKIP(Status.SKIP),
    FAIL(Status.FAIL),
    PASS(Status.PASS);

    private final Status status;

    LogStatus(Status status) { this.status = status; }

    public Status get() { return this.status; }

}
