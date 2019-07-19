package pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import entities.User;
import sections.LoginForm;

@Url("/index.html")
@Title("Home Page")
public class HomePage extends WebPage {

    private LoginForm loginForm;

    public void loginAs(User user) {
        loginForm.loginAs(user);
    }
}
