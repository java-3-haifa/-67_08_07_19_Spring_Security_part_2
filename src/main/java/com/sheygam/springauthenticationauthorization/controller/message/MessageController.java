package com.sheygam.springauthenticationauthorization.controller.message;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@RestController
public class MessageController {
    private ConcurrentHashMap<String, CopyOnWriteArrayList<MessageEntity>> map = new ConcurrentHashMap<>();


    public MessageController() {
        fill();
    }

    @GetMapping("users")
    public List<ResponseMessageDto> getAllMessage(){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(principal.getName());
        return toResponseList(map.getOrDefault(principal.getName(),new CopyOnWriteArrayList<>()));
    }

    @GetMapping("admin/all")
    public Map<String,List<ResponseMessageDto>> getAllMessages(){
        return map.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> toResponseList(e.getValue())
                ));
    }

    private void fill(){
        map.computeIfAbsent("john@mail.com",k->new CopyOnWriteArrayList<>()).add(new MessageEntity(UUID.randomUUID(),LocalDateTime.now(),"Message 1"));
        map.computeIfAbsent("john@mail.com",k->new CopyOnWriteArrayList<>()).add(new MessageEntity(UUID.randomUUID(),LocalDateTime.now(),"Message 2"));
        map.computeIfAbsent("john@mail.com",k->new CopyOnWriteArrayList<>()).add(new MessageEntity(UUID.randomUUID(),LocalDateTime.now(),"Message 3"));
        map.computeIfAbsent("sara@mail.com",k->new CopyOnWriteArrayList<>()).add(new MessageEntity(UUID.randomUUID(),LocalDateTime.now(),"Message 4"));
        map.computeIfAbsent("sara@mail.com",k->new CopyOnWriteArrayList<>()).add(new MessageEntity(UUID.randomUUID(),LocalDateTime.now(),"Message 5"));
        map.computeIfAbsent("sara@mail.com",k->new CopyOnWriteArrayList<>()).add(new MessageEntity(UUID.randomUUID(),LocalDateTime.now(),"Message 6"));
    }
    //Mappers start >>>>>>>>>>>>>>>>>>>>>>
    private MessageEntity toMessageEntity(RequestMessageDto messageDto) {
        MessageEntity res = new MessageEntity();
        res.message = messageDto.message;
        res.dateTime = LocalDateTime.now();
        res.id = UUID.randomUUID();
        return res;
    }

    private List<ResponseMessageDto> toResponseList(List<MessageEntity> list){
       return list.stream().map(this::toMessageDto).collect(Collectors.toList());
    }

    private ResponseMessageDto toMessageDto(MessageEntity messageEntity) {
        ResponseMessageDto res = new ResponseMessageDto();
        if(messageEntity != null) {
            res.id = messageEntity.id.toString();
            res.dateTime = messageEntity.dateTime;
            res.message = messageEntity.message;
        }
        return res;
    }

    //Mappers end <<<<<<<<<<<<<<<<<<<<<<<<



}
