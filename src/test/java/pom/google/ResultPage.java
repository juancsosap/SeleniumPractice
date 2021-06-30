package pom.google;

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
        page.searchText("test", miliseconds);
    }

    public static final By results = By.xpath("//div[@id='rso']//div[@class='g']");

}
