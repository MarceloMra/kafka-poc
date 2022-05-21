package com.example.kafkapoc.dao;

import com.example.kafkapoc.dto.MessageDTO;
import com.example.kafkapoc.model.Message;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.UUID;

@Repository
public class MessageRedisDAO {
    private Jedis jedis = new Jedis("localhost", 6379);

    public Message create(MessageDTO messageDTO) {
        Message message = new Message(UUID.randomUUID().toString(), messageDTO.getMessage());
        //TODO Sent it to kafka to be consumed and then registered in redis
        jedis.set(message.getId(), message.getMessage());
        if(jedis.get(message.getId()) != null){
            return message;
        }
        return null;
    }

    public Message findMessage(String id) {
        String message = jedis.get(id);
        if(message != null){
            return new Message(id, message);
        }
        return null;
    }

    public Set<String> findMessages() {
        return jedis.keys("*");
    }

    public Message deleteMessage(String messageId) {
        Message message = new Message();
        message.setMessage(jedis.get(messageId));
        if(message.getMessage() != null){
            jedis.del(messageId);
            message.setId(messageId);
            return message;
        }
        return null;
    }
}
