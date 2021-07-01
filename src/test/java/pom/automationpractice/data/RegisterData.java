package pom.automationpractice.data;

public class RegisterData {

    public RegisterData() {
        title = firstname = lastname = password = company = alias = null; // ""
        dayBirth = monthBirth = yearBirth = state = country = null; // "-"
        address = city = postCode = additional = homePhone = mobilePhone = null; // ""
    }

    public String title, firstname, lastname, password, dayBirth, monthBirth, yearBirth, company;
    public String address, city, state, postCode, country, additional, homePhone, mobilePhone, alias;

}
