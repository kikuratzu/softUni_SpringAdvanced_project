package kristianVoda.kristianVoda.Services;

import kristianVoda.kristianVoda.DTO.MessageDTO;
import kristianVoda.kristianVoda.Entity.Message;
import kristianVoda.kristianVoda.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public MessageDTO createMessage(MessageDTO dto){
        messageRepository.save(Message.builder().name(dto.getName()).email(dto.getEmail()).message(dto.getMessage()).build());
        return dto;
    }
}
