import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import entities.MetalsAndColorsPageData;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static com.epam.jdi.light.driver.get.DriverData.CHROME_OPTIONS;
import static com.epam.jdi.light.logger.LogLevels.STEP;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static com.epam.jdi.light.ui.html.PageFactory.initElements;
import static entities.Defaults.DEFAULT_USER;
import static enums.HeaderMenu.METALS_AND_COLORS;

public class MetalsAndColorsPageTest {

    private static final String METALS_COLORS_DATA_SET = ".\\src\\test\\resources\\JDI_ex8_metalsColorsDataSet.json";

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        CHROME_OPTIONS = () -> {
            ChromeOptions cap = new ChromeOptions();
            cap.addArguments("--start-maximized");
            return cap;
        };
        logger.setLogLevel(STEP);
        initElements(SiteJdi.class);
    }

    @BeforeMethod(alwaysRun = true)
    public void openSite() {
        SiteJdi.homePage.open();
        logger.info("Run Tests");
    }

    @AfterMethod(alwaysRun = true)
    public void logout() {
        SiteJdi.logout();
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() {
        killAllSeleniumDrivers();
    }

    @DataProvider(name = "data")
    public Object[] getData() throws FileNotFoundException {
        Map<String, MetalsAndColorsPageData> dataSets = new HashMap<>();
        Gson gson = new Gson();

        try {
        BufferedReader br = new BufferedReader(new FileReader(METALS_COLORS_DATA_SET));
        Type type = new TypeToken<Map<String, MetalsAndColorsPageData>>() {
        }.getType();
            dataSets = gson.fromJson(br, type);
        } catch (Exception e) {
            System.out.println(e);
        }

        return dataSets.values().toArray();
    }

    @Test(dataProvider = "data")
    public void metalsAndColorsPageTest(MetalsAndColorsPageData data) {

        //Login on JDI site as User
        SiteJdi.homePage.loginAs(DEFAULT_USER);

        //Piter_Chailovskii is logged in
        // TODO This method should be parametrised exactly the same value like SiteJdi.homePage::loginAs
        SiteJdi.userName.assertThat().text(DEFAULT_USER.getFullName());

        //Open Metals & Colors page by Header menu
        SiteJdi.headerMenu.select(METALS_AND_COLORS);

        //Metals & Colors page is opened
        SiteJdi.metalsAndColorsPage.checkOpened();

        //Fill form Metals & Colors by data
        SiteJdi.metalsAndColorsPage.fillForm(data);

        //Result sections should contains data
        SiteJdi.metalsAndColorsPage.checkResult(data);
    }
}
