package kristianVoda.kristianVoda.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO extends BaseDTO {
    private String name;
    private String email;
    private String message;
}
