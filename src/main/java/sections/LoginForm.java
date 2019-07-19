package sections;

import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.ui.html.common.Button;
import com.epam.jdi.light.ui.html.common.TextField;
import entities.User;

public class LoginForm extends Form<User> {
    @Css("#name")
    TextField name;

    @Css("#password")
    TextField password;

    @Css("#login-button")
    Button submit;

    @Css("#user-icon")
    private static Button userIcon;

    public void loginAs(User user) {
        userIcon.click();
        name.input(user.getName());
        password.input(user.getPassword());
        submit.click();
    }
}