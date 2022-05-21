package com.example.kafkapoc.service;

import com.example.kafkapoc.dao.MessageRedisDAO;
import com.example.kafkapoc.dto.MessageDTO;
import com.example.kafkapoc.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MessageService {
    @Autowired
    private MessageRedisDAO messageRedisDAO;
    public Message createMessage(MessageDTO messageDTO){
        return messageRedisDAO.create(messageDTO);
    }

    public Message findMessageById(String id){
        return messageRedisDAO.findMessage(id);
    }

    public Set<String> findAllMessages(){
        return messageRedisDAO.findMessages();
    }

    public Message deleteMessageById(String messageId){
        return messageRedisDAO.deleteMessage(messageId);
    }
}
