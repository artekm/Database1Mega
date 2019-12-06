package artur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
    private Integer Id;
    private String firstName;
    private String lastName;
    private String pesel;
}
