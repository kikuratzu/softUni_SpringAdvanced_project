package kristianVoda.kristianVoda.Entity;

import jakarta.persistence.*;
import kristianVoda.kristianVoda.enums.WaterTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderedItems")
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "waterType")
    @Enumerated(EnumType.STRING)
    private WaterTypes waterType;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "orderItem")
    private List<CartItem> cartItems = new ArrayList<>();

    @ManyToOne
    private OrderItem orderItem;


}
