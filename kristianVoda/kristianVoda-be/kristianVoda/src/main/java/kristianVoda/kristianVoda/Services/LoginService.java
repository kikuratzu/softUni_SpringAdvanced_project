package kristianVoda.kristianVoda.Services;


import kristianVoda.kristianVoda.DTO.CreateUserDTO;
import kristianVoda.kristianVoda.DTO.LoginUserDTO;
import kristianVoda.kristianVoda.Entity.Client;
import kristianVoda.kristianVoda.enums.SaltWord;
import kristianVoda.kristianVoda.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class LoginService {

@Autowired
 private UserRepository repo;

private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Transactional(readOnly = true)
    public boolean login(final LoginUserDTO dto) {
        if (dto.getEmailOrUsername().endsWith("@gmail.com") || dto.getEmailOrUsername().endsWith("@abv.bg")) {
            Client client = repo.findByEmail(dto.getEmailOrUsername());
            if (client != null) {
                return encoder.matches(dto.getPassword() + SaltWord._GP3R$L8AAM_$K_S, client.getPassword());
            } else {
                return false;
            }
        } else {
            Client client = repo.findByUsername(dto.getEmailOrUsername());
            if (client != null) {
                return encoder.matches(dto.getPassword() + SaltWord._GP3R$L8AAM_$K_S, client.getPassword());
            } else {
                return false;
            }
        }
    }

    Client findClientById(final UUID id){
        return repo.findByIdOrNull(id);
    }

    @Transactional
    public boolean create(final CreateUserDTO dto){
       Client user = new Client();
       user.setEmail(dto.getEmail());
       user.setUsername(dto.getUsername());
       user.setPassword(encoder.encode(dto.getPassword() + SaltWord._GP3R$L8AAM_$K_S));
       user.setFirstName(dto.getFirstName());
       user.setLastName(dto.getLastName());

        repo.save(user);
        return true;

    }

    @Transactional(readOnly = true)
    public boolean alreadyExistingUserByEmail(final String email){
       return repo.existsByEmail(email);

    }

    @Transactional(readOnly = true)
    public boolean alreadyExistingUserByUsername(final String username){
        return repo.existsByUsername(username);

    }
    @Transactional(readOnly = true)
    public UUID findForgotPasswordDTOByEmail(final String email){
       return repo.findByEmail(email).getId();
    }

    @Transactional
    public boolean setNewPassword(final UUID id, final String password){
        try {
            Client user = repo.findByIdOrNull(id);
            user.setPassword(encoder.encode(password + SaltWord._GP3R$L8AAM_$K_S));
            repo.save(user);
        }
        catch (Exception e){
            return false;

        }
        return true;
    }

    @Transactional(readOnly = true)
    public UUID findUserIdByUsernameOrEmail(final String usernameOrEmail){
        if (usernameOrEmail.endsWith("@gmail.com") || usernameOrEmail.endsWith("@abv.bg")){
            return repo.findByEmail(usernameOrEmail).getId();
        }
        return repo.findByUsername(usernameOrEmail).getId();
    }




}


