package sections;

import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.ui.html.common.Button;
import com.epam.jdi.light.ui.html.common.TextField;
import entities.User;

public class LoginForm extends Form<User> {
    @FindBy(css = "#name")
    TextField name;

    @FindBy(css = "#password")
    TextField password;

    @FindBy(css = "#login-button")
    Button submit;

    @FindBy(id = "user-icon")
    private static Button userIcon;

    public void loginAs(User user) {
        userIcon.click();
        name.input(user.getName());
        password.input(user.getPassword());
        submit.click();
    }
}