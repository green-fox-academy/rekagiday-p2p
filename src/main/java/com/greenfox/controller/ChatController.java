package com.greenfox.controller;

import com.greenfox.logging.RequestLogger;
import com.greenfox.model.Message;
import com.greenfox.model.Messages;
import com.greenfox.model.User;
import com.greenfox.repository.MessageRepository;
import com.greenfox.repository.UserRepository;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  @Autowired
  RequestLogger requestLogger;

  @Autowired
  UserRepository userRepository;

  @Autowired
  MessageRepository messageRepository;

  @Autowired
  User user;

  @Autowired
  Messages messages;

  @GetMapping(value = "/login")
  public void home(HttpServletRequest request) {
    try {
      requestLogger.info(request);
      messages.addAll();
    } catch (Exception e) {
      requestLogger.error(request);
    }
  }

  @PostMapping(value = "/register")
  public void saveUser(HttpServletResponse response, String username)
      throws IOException {
    if (username != null) {
      user.setUsername(username);
      userRepository.save(user);
      response.sendRedirect("?username=" + user.getUsername());
    } else {
      user.setUsername("");
      response.sendRedirect("/registererror");
    }
  }

  @PostMapping(value = "/savemessage")
  public void saveMessage(HttpServletResponse response, String message) throws IOException {
    Message myMessage = new Message();
    myMessage.setUsername(user.getUsername());
    myMessage.setMessage(message);
    myMessage.setTimestamp(System.currentTimeMillis());
    messageRepository.save(myMessage);
    messages.addAll();
    response.sendRedirect("?username=" + user.getUsername());
  }
}

