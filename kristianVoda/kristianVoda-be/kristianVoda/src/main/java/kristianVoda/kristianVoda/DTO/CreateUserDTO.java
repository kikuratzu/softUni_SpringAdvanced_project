package kristianVoda.kristianVoda.DTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO extends BaseDTO {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
