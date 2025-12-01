package kristianVoda.kristianVoda.Services;

import kristianVoda.kristianVoda.Entity.Client;
import kristianVoda.kristianVoda.configs.CustomUserDetails;
import kristianVoda.kristianVoda.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsername(username);
        if (client == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(client);
    }
}