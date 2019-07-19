package pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import entities.MetalsAndColorsPageData;
import sections.MetalsAndColorsForm;

@Url("/metals-colors.html")
@Title("Metal and Colors")
public class MetalsAndColorsPage extends WebPage {

    private MetalsAndColorsForm metalsAndColorsForm;

    public void fillForm(MetalsAndColorsPageData metalsAndColorsPageData) {
        metalsAndColorsForm.fillForm(metalsAndColorsPageData);
    }

    public void checkResult(MetalsAndColorsPageData metalsAndColorsPageData) {
        metalsAndColorsForm.checkResult(metalsAndColorsPageData);
    }
}
