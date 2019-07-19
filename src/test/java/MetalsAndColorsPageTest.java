import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static com.epam.jdi.light.driver.get.DriverData.CHROME_OPTIONS;
import static com.epam.jdi.light.logger.LogLevels.STEP;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static com.epam.jdi.light.ui.html.PageFactory.initElements;
import static entities.Defaults.DEFAULT_DATA;
import static entities.Defaults.DEFAULT_USER;
import static enums.HeaderMenu.METALS_AND_COLORS;

public class MetalsAndColorsPageTest {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        CHROME_OPTIONS = () -> {
            ChromeOptions cap = new ChromeOptions();
            cap.addArguments("--start-maximized");
            return cap;
        };
        logger.setLogLevel(STEP);
        initElements(SiteJdi.class);
        SiteJdi.homePage.open();
        logger.info("Run Tests");
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() {
        killAllSeleniumDrivers();
    }

    @Test
    public void metalsAndColorsPageTest() {

        //Login on JDI site as User
        SiteJdi.homePage.loginAs(DEFAULT_USER);

        //Piter_Chailovskii is logged in
        SiteJdi.userName.assertThat().text(DEFAULT_USER.getFullName());

        //Open Metals & Colors page by Header menu
        SiteJdi.headerMenu.select(METALS_AND_COLORS);

        //Metals & Colors page is opened
        SiteJdi.metalsAndColorsPage.checkOpened();

        //Fill form Metals & Colors by data
        SiteJdi.metalsAndColorsPage.fillForm(DEFAULT_DATA);

        //Result sections should contains data
        SiteJdi.metalsAndColorsPage.checkResult(DEFAULT_DATA);
    }
}
