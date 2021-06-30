package pom.automationpractice;

import org.openqa.selenium.By;
import utils.pom.Page;
import utils.web.Browser;

public class ResultPage extends Page {

    public ResultPage(Browser browser) {
        super(browser);
    }

    public void go(int miliseconds) {
        HomePage page = new HomePage(browser);
        page.go(miliseconds);
        page.searchProduct("blouse", miliseconds);
    }

    public static final By textHeading = By.xpath("//span[@class='navigation_page']");

    public static final By produstResults = By.xpath("//ul[contains(@class, 'product_list')]//li");

}
