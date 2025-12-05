package kristianVoda.kristianVoda.Entity;
import jakarta.persistence.*;
import kristianVoda.kristianVoda.enums.WaterTypes;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cartItems")
public class CartItem {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    private Order order;

    @Column
    @Enumerated(EnumType.STRING)
    private WaterTypes waterType;

    @Column
    private Integer quantity;


    @ManyToOne
    private OrderItem orderItem;



}
