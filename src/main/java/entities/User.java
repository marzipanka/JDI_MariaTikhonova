package entities;

import com.epam.jdi.tools.DataClass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends DataClass<User> {
    private String name;
    private String password;
    private String fullName;
}
