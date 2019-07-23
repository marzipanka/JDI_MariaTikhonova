import com.epam.jdi.light.elements.base.UIElement;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.UI;
import com.epam.jdi.light.ui.html.complex.Menu;
import entities.User;
import pages.*;
import org.openqa.selenium.WebElement;

public class SiteJdi {

    public static HomePage homePage;
    public static MetalsAndColorsPage metalsAndColorsPage;

    @Css(".profile-photo [ui=label]") public static UIElement userName;
    @Css(".dropdown-menu-login") public static WebElement logout;
    @Css("img#user-icon") public static WebElement userIcon;
    @UI("nav ul li") public static Menu headerMenu;

    public static void logout() {
        userIcon.click();
        logout.click();
    }

    public static void assertUserName(User user) {
        userName.assertThat().text(user.getFullName());
    }
}
