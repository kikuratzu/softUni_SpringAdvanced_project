package kristianVoda.kristianVoda.repo;
import kristianVoda.kristianVoda.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Client, UUID> {
    default Client findByIdOrNull(UUID id) {
        return findById(id).orElse(null);
    }
   Client findByEmail(String email);
    Client findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
