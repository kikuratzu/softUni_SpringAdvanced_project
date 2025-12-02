package kristianVoda.kristianVoda.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
    private String name;
    private String email;
    private String message;
}
