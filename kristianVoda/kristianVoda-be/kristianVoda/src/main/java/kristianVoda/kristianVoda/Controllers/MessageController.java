package kristianVoda.kristianVoda.Controllers;
import kristianVoda.kristianVoda.DTO.MessageDTO;
import kristianVoda.kristianVoda.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("contact")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class MessageController {

    @Autowired
    private MessageService service;

    @PostMapping("/createMessage")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageDTO> createMessage(@RequestBody final MessageDTO dto
    ) {
        return new ResponseEntity<>(service.createMessage(dto), HttpStatus.OK);
    }
}
