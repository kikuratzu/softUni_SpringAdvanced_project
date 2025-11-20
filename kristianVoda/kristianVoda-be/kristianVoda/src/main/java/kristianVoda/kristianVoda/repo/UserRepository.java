package kristianVoda.kristianVoda.repo;

import kristianVoda.kristianVoda.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Client, Long> {
    default Client findByIdOrNull(Long id) {
        return findById(id).orElse(null);
    }
   Client findByEmail(String email);
    Client findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
