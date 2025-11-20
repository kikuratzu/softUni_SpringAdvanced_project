package kristianVoda.kristianVoda.repo;

import kristianVoda.kristianVoda.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    default CartItem findByIdOrNull(Long id) {
        return findById(id).orElse(null);
    }

}
