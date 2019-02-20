package lahsivjar.spring.websocket.template;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MessageController {

    @Autowired
    private ChatHistoryDao chatHistoryDao;


    @Autowired
    private SimpMessagingTemplate template;

    /*
     * This MessageMapping annotated method will be handled by
     * SimpAnnotationMethodMessageHandler and after that the Message will be
     * forwarded to Broker channel to be forwarded to the client via WebSocket
     */
    @MessageMapping("/all")
    public void post(@Payload Map<String, String> message) {
        message.put("timestamp", Long.toString(System.currentTimeMillis()));
        System.out.println(message);
        chatHistoryDao.save(message);
        template.convertAndSend("/topic/"+message.get("authorId"), message);
    }

    @RequestMapping("/history")
    public List<Map<String, String>> getChatHistory() {
        System.out.println("Bármi");
        return chatHistoryDao.get();
    }
}
