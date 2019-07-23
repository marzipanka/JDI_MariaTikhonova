package pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.ui.html.common.Label;
import com.epam.jdi.light.ui.html.common.Text;
import entities.MetalsAndColorsPageData;
import sections.MetalsAndColorsForm;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@Url("/metals-colors.html")
@Title("Metal and Colors")
public class MetalsAndColorsPage extends WebPage {

    private MetalsAndColorsForm metalsAndColorsForm;

    @Css(".summ-res")
    private Label summaryLogRow;

    @Css(".elem-res")
    private Text elementsLogRow;

    @Css(".col-res")
    private Text colorsLogRow;

    @Css(".met-res")
    private Text metalsLogRow;

    @Css(".sal-res")
    private Text vegetablesLogRow;

    public void fillForm(MetalsAndColorsPageData metalsAndColorsPageData) {
        metalsAndColorsForm.fillForm(metalsAndColorsPageData);
    }

    public void checkResult(MetalsAndColorsPageData data) {
        if ((data.getSummary().get(0) != null) && (data.getSummary().get(1) != null)) {
            checkSummaryLogRow(data.getSummary().get(0), data.getSummary().get(1));
        }
        if (data.getElements() != null) {
            checkElementsLogRow(data.getElements());
        }
        if (data.getColor() != null) {
            checkColorsLogRow(data.getColor());
        }
        if (data.getMetals() != null) {
            checkMetalsLogRow(data.getMetals());
        }
        if (data.getVegetables() != null) {
            checkVegetablesLogRow(data.getVegetables());
        }
    }

    private void checkSummaryLogRow(Integer summaryNumber1, Integer summaryNumber2) {
        String sum = summaryNumber1 + summaryNumber2 + "";
        summaryLogRow.assertThat().text(equalTo(String.format("Summary: %s", sum)));
    }

    private void checkElementsLogRow(List<String> elements) {
        // fixed TODO Oh my, take a look on Strings::join method
        String elementsRow = String.join(", ", elements);
        elementsLogRow.assertThat().text(equalTo(String.format("Elements: %s", elementsRow)));
    }

    private void checkColorsLogRow(String color) {
        colorsLogRow.assertThat().text(equalTo(String.format("Color: %s", color)));
    }

    private void checkMetalsLogRow(String metal) {
        metalsLogRow.assertThat().text(equalTo(String.format("Metal: %s", metal)));
    }

    private void checkVegetablesLogRow(List<String> vegetables) {
        // fixed TODO Same story like 123 line
        String vegetablesRow = String.join(", ", vegetables);
        vegetablesLogRow.assertThat().text(equalTo(String.format("Vegetables: %s", vegetablesRow)));
    }
    // fixed !TODO
}
