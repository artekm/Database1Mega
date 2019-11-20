package artur.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
    private Integer Id;
    private String first_name;
    private String last_name;
}
