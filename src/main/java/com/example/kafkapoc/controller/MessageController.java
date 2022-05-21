package com.example.kafkapoc.controller;

import com.example.kafkapoc.dto.MessageDTO;
import com.example.kafkapoc.model.Message;
import com.example.kafkapoc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message createMessage(@RequestBody MessageDTO messageDTO){
        return messageService.createMessage(messageDTO);
    }

    @GetMapping
    public List<Message> findMessages(){
        return messageService.findAllMessages();
    }

    @GetMapping("/{messageId}")
    public Message findMessage(@PathVariable String messageId){
        return messageService.findMessageById(messageId);
    }

    @DeleteMapping("/{messageId}")
    public Message deleteMessage(@PathVariable String messageId){
        return messageService.deleteMessageById(messageId);
    }
}
