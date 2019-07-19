package sections;

import com.epam.jdi.light.elements.base.UIElement;
import com.epam.jdi.light.elements.complex.Droplist;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.XPath;
import com.epam.jdi.light.ui.html.common.Button;
import com.epam.jdi.light.ui.html.common.Label;
import com.epam.jdi.light.ui.html.common.Text;
import com.epam.jdi.light.ui.html.complex.RadioButtons;
import entities.MetalsAndColorsPageData;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class MetalsAndColorsForm extends Form<MetalsAndColorsPageData> {

    @XPath("//*[@id='odds-selector']//label[contains(text(), '%s')]")
    private RadioButtons oddNumbers;

    @XPath("//*[@id='even-selector']//label[contains(text(), '%s')]")
    private RadioButtons evenNumbers;

    @Css("#calculate-button")
    private Button calculateButton;

    @XPath("//p[@class='checkbox'][contains(., '%s')]//label")
    private UIElement elements;

    @JDropdown(root = "div[ui=dropdown]",
            value = ".filter-option",
            list = "li",
            expand = ".caret")
    private Droplist colorsDropdown;

    @JDropdown(root = "div[ui=combobox]",
            value = ".filter-option",
            list = "li",
            expand = ".caret")
    private Droplist metalsDropdown;

    @JDropdown(root = "#salad-dropdown",
            value = "[data-toggle='dropdown']",
            list = "li",
            expand = ".caret")
    private Droplist vegetablesDropdown;

    @XPath("//div[@id='salad-dropdown']/button")
    private Button vegetablesButton;

    @Css("#submit-button")
    private Button submitButton;

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
        //fill numbers
        oddNumbers.select(metalsAndColorsPageData.getSummary().get(0).toString());
        evenNumbers.select(metalsAndColorsPageData.getSummary().get(1).toString());
        calculateButton.click();

        //fill elements
        for (String element : metalsAndColorsPageData.getElements()) {
            elements.select(element);
        }

        //fill color
        colorsDropdown.select(metalsAndColorsPageData.getColor());

        //fill metal
        metalsDropdown.select(metalsAndColorsPageData.getMetals());

        //fill vegetables
        vegetablesButton.click();
        vegetablesDropdown.select("Vegetables");
        for (String vegetable : metalsAndColorsPageData.getVegetables()) {
            vegetablesDropdown.select(vegetable);
        }

        submitButton.click();
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
        String elementsRow = elements.get(0);
        if (elements.size() > 1) {
            for (int i = 1; i < elements.size(); i++) {
                elementsRow += ", " + elements.get(i);
            }
        }
        elementsLogRow.assertThat().text(equalTo(String.format("Elements: %s", elementsRow)));
    }

    private void checkColorsLogRow(String color) {
        colorsLogRow.assertThat().text(equalTo(String.format("Color: %s", color)));
    }

    private void checkMetalsLogRow(String metal) {
        metalsLogRow.assertThat().text(equalTo(String.format("Metal: %s", metal)));
    }

    private void checkVegetablesLogRow(List<String> vegetables) {
        String vegetablesRow = vegetables.get(0);
        if (vegetables.size() > 1) {
            for (int i = 1; i < vegetables.size(); i++) {
                vegetablesRow += ", " + vegetables.get(i);
            }
        }
        vegetablesLogRow.assertThat().text(equalTo(String.format("Vegetables: %s", vegetablesRow)));
    }
}