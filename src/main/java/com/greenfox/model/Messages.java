package com.greenfox.model;

import com.greenfox.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Messages {

  Iterable<Message> messages;

  @Autowired
  MessageRepository messageRepository;

  public void addAll() {
    messages = messageRepository.findAllByOrderByTimestampDesc();
  }

  public Messages() {
  }

  public void setMessages(Iterable<Message> messages) {
    this.messages = messages;
  }

  public Iterable<Message> getMessages() {
    return messages;
  }
}
