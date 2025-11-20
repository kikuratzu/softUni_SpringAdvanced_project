package kristianVoda.kristianVoda.DTO;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
