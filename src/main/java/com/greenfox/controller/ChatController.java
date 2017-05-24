package com.greenfox.controller;

import com.greenfox.logging.RequestLogger;
import com.greenfox.model.Message;
import com.greenfox.model.ReceivedMessage;
import com.greenfox.model.Response;
import com.greenfox.model.User;
import com.greenfox.repository.MessageRepository;
import com.greenfox.repository.UserRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin("*")
public class ChatController {

  @Autowired
  RequestLogger requestLogger;

  @Autowired
  UserRepository userRepository;

  @Autowired
  MessageRepository messageRepository;

  @Autowired
  User user;

  @GetMapping(value = "/login")
  public void home(HttpServletRequest request) {
    try {
      requestLogger.info(request);
    } catch (Exception e) {
      requestLogger.error(request);
    }
  }


  @CrossOrigin("*")
  @PostMapping(value = "/api/message/receive")
  public Response receiveMessage(@RequestBody ReceivedMessage receivedMessage) {
    Message received = new Message();
    List<String> errors = new ArrayList<>();
    if (StringUtils.isEmpty(receivedMessage.getMessage().getText())) {
      errors.add("message.text");
    } else {
      received.setText(receivedMessage.getMessage().getText());
    }
    if (StringUtils.isEmpty(receivedMessage.getMessage().getUsername())) {
      errors.add("message.username");
    } else {
      received.setUsername(receivedMessage.getMessage().getUsername());
    }
    if (StringUtils.isEmpty(receivedMessage.getMessage().getTimestamp())) {
      errors.add("message.timestamp");
    } else {
      received.setTimestamp(receivedMessage.getMessage().getTimestamp());
    }
    if (StringUtils.isEmpty(receivedMessage.getMessage().getId())) {
      errors.add("message.id");
    } else {
      received.setId(receivedMessage.getMessage().getId());
    }
    if (StringUtils.isEmpty(receivedMessage.getClient().getId())) {
      errors.add("client.id");
    }
    messageRepository.save(received);

    Response response = new Response();
    if (errors.size() == 0) {
      response.setStatus("ok");
    } else {
      response.setStatus("error");
      response.setMessage(errors);
    }
    return response;
  }
}




