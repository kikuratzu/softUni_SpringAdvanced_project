package kristianVoda.kristianVoda.DTO;

import kristianVoda.kristianVoda.enums.WaterTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO extends BaseDTO {

    private WaterTypes type;
    private Integer quantity;

}
