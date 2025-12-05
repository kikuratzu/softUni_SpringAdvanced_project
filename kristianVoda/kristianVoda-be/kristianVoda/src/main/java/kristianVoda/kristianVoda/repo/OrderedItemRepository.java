package kristianVoda.kristianVoda.repo;
import kristianVoda.kristianVoda.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderItem, UUID> {
    @Query("SELECT o FROM OrderedItem o WHERE o.order.id = :orderId")
    Optional<List<OrderItem>> findAllOrderItemsByOrderId(@Param("orderId") UUID orderId);
}
