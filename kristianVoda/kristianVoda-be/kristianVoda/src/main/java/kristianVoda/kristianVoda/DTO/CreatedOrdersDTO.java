package kristianVoda.kristianVoda.DTO;
import kristianVoda.kristianVoda.enums.WaterTypes;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatedOrdersDTO extends BaseDTO {
    private UUID orderId;
    private WaterTypes types;
    private int quantity;

}
