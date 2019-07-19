package entities;

import com.epam.jdi.tools.DataClass;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MetalsAndColorsPageData extends DataClass<MetalsAndColorsPageData> {
    private List<Integer> numbers;
    private List<String> elements;
    private String color;
    private String metals;
    private List<String> vegetables;
}
