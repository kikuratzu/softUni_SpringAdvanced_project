package kristianVoda.kristianVoda.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends BaseDTO {

    private String address;
    private String phoneNumber;

}
