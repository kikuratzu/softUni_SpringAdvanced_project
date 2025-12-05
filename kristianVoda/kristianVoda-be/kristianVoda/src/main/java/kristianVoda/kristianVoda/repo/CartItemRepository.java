package kristianVoda.kristianVoda.repo;
import kristianVoda.kristianVoda.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    default CartItem findByIdOrNull(UUID id) {
        return findById(id).orElse(null);
    }
}
