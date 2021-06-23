package pom.automationpractice;

import org.openqa.selenium.By;
import utils.pom.Page;
import utils.web.Browser;

public class ProfilePage extends Page {

    public ProfilePage(Browser browser) {
        super(browser);
    }

    public void go(int miliseconds) {
        HomePage page = new HomePage(browser);
        page.go(miliseconds);
    }

    public static final By textHeading = By.xpath("//span[@class='navigation_page']");

}
