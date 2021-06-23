package utils.pom;

import utils.web.Browser;

public abstract class Page {

    protected Browser browser;
    protected String url;

    public Page(Browser browser) {
        this.browser = browser;
    }

    public abstract void go(int miliseconds);

    public Browser getBrowser() {
        return browser;
    }

}
