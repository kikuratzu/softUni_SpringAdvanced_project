package kristianVoda.kristianVoda.DTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO extends BaseDTO {
    private String emailOrUsername;
    private String password;
}
