package steps;

import utils.pom.Page;
import utils.reports.Reporter;
import utils.tests.Asserter;
import utils.web.Browser;

public class PageSteps {

    public static Browser browser = null;
    public static Asserter asserter = null;
    public static Reporter reporter = null;

    public static final int wait = 1000;
    public static final String resourceFolder = "src/test/resources/";
    public static final String reportFolder = "target/reports/";

}
