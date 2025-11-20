package kristianVoda.kristianVoda.Controllers;

import kristianVoda.kristianVoda.DTO.CreateUserDTO;
import kristianVoda.kristianVoda.DTO.LoginUserDTO;
import kristianVoda.kristianVoda.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("login")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class UserController {

    @Autowired
   private LoginService service;

    @PostMapping
    public boolean checkForRightCredentials(
            @RequestBody LoginUserDTO dto
            ) {
        return service.login(dto);
    }

    @PostMapping("/createUser")
    public boolean create(
            @RequestBody CreateUserDTO dto
            ) {
        return service.create(dto);
    }

    @GetMapping("/createUser/email")
    public boolean alreadyExistingUserByEmail(
            @RequestParam final String email
    ) {
        return service.alreadyExistingUserByEmail(email);
    }

    @GetMapping("/createUser/username")
    public boolean alreadyExistingUserByUsername(
            @RequestParam final String username
    ) {
        return service.alreadyExistingUserByUsername(username);
    }

    @GetMapping("/forgotPassword/email")
    public Long resetPassword(
            @RequestParam final String email
    ) {
        return service.findForgotPasswordDTOByEmail(email);
    }

    @PatchMapping("/forgotPassword/password/{id}")
    public boolean setNewPassword(
            @PathVariable final Long id,
            @RequestParam final String password
    ) {
        return service.setNewPassword(id, password);
    }

    @GetMapping("/successfulLogin")
    public Long findUserIdByUsernameOrEmail(
            @RequestParam final String usernameOrEmail
    ) {
        return service.findUserIdByUsernameOrEmail(usernameOrEmail);
    }



}