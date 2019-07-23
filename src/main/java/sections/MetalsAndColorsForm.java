package sections;

import com.epam.jdi.light.elements.base.UIElement;
import com.epam.jdi.light.elements.complex.Droplist;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.XPath;
import com.epam.jdi.light.ui.html.common.Button;
import com.epam.jdi.light.ui.html.complex.RadioButtons;
import entities.MetalsAndColorsPageData;

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

    // fixed TODO Basically, this elements hav no relation with MetalsAndColorsForm, they should not be here.
    // fixed !TODO

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

    // fixed TODO Same story like line 57
}