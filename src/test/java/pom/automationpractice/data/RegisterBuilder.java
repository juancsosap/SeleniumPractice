package pom.automationpractice.data;

import pom.automationpractice.RegisterPage;
import utils.web.Browser;

public class RegisterBuilder {

    private RegisterData data;
    private RegisterPage page;
    private Browser browser;

    public RegisterBuilder(RegisterPage page) {
        this.data = new RegisterData();
        this.page = page;
    }

    public RegisterBuilder title(String value) { data.title = value; return this; }
    public RegisterBuilder firstname(String value) { data.firstname = value; return this; }
    public RegisterBuilder lastname(String value) { data.lastname = value; return this; }
    public RegisterBuilder password(String value) { data.password = value; return this; }
    public RegisterBuilder birthDay(String value) {  data.dayBirth = value; return this; }
    public RegisterBuilder birthMonth(String value) {  data.monthBirth = value; return this; }
    public RegisterBuilder birthYear(String value) {  data.yearBirth = value; return this; }
    public RegisterBuilder company(String value) { data.company = value; return this; }
    public RegisterBuilder address(String value) { data.address = value; return this; }
    public RegisterBuilder city(String value) { data.city = value; return this; }
    public RegisterBuilder state(String value) { data.state = value; return this; }
    public RegisterBuilder postCode(String value) { data.postCode = value; return this; }
    public RegisterBuilder country(String value) { data.country = value; return this; }
    public RegisterBuilder additional(String value) { data.additional = value; return this; }
    public RegisterBuilder homePhone(String value) { data.homePhone = value; return this; }
    public RegisterBuilder mobilePhone(String value) { data.mobilePhone = value; return this; }
    public RegisterBuilder alias(String value) { data.alias = value; return this; }

    public void register(int miliseconds) {
        page.register(data, miliseconds);
    }

}
