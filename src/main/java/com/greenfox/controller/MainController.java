package com.greenfox.controller;

import com.greenfox.logging.RequestLogger;
import com.greenfox.model.Client;
import com.greenfox.model.Message;
import com.greenfox.model.ReceivedMessage;
import com.greenfox.model.Response;
import com.greenfox.model.User;
import com.greenfox.repository.MessageRepository;
import com.greenfox.repository.UserRepository;
import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

  @Autowired
  RequestLogger requestLogger;

  @Autowired
  MessageRepository messageRepository;

  @Autowired
  User user;

  @Autowired
  UserRepository userRepository;

  RestTemplate restTemplate = new RestTemplate();

  @RequestMapping(value = "/")
  public ModelAndView home(HttpServletRequest request) {
    ModelAndView modelAndView = new ModelAndView("index");
    try {
      requestLogger.info(request);
      modelAndView.addObject("messages", messageRepository.findAllByOrderByTimestampDesc());
      modelAndView.addObject("username", user.getUsername());
    } catch (Exception e) {
      requestLogger.error(request);
    }
    return modelAndView;
  }

  @RequestMapping(value = "/enter")
  public String register(HttpServletRequest request) {
    try {
      requestLogger.info(request);
    } catch (Exception e) {
      requestLogger.error(request);
    }
    return "register";
  }

  @PostMapping(value = "/savemessage")
  public ModelAndView forwardAndSave(String text) throws IOException {
    Message myMessage = new Message();
    ModelAndView modelAndView = new ModelAndView("redirect:/");
    myMessage.setUsername(user.getUsername());
    myMessage.setText(text);
    myMessage.setTimestamp(new Timestamp(System.currentTimeMillis()));

    Client client = new Client();
    client.setId("rekagiday");
    ReceivedMessage receivedMessage = new ReceivedMessage();
    receivedMessage.setMessage(myMessage);
    receivedMessage.setClient(client);
    messageRepository.save(myMessage);
    restTemplate.postForObject( "https://chat-p2p.herokuapp.com/api/message/receive", receivedMessage, Response.class);

    return modelAndView;
  }


  @PostMapping(value = "/register")
  public ModelAndView saveUser(HttpServletResponse response, String username)
      throws IOException {
    if (!StringUtils.isEmpty(username)) {
      ModelAndView modelAndView = new ModelAndView("redirect:/");
      user.setId(1l);
      user.setUsername(username);
      userRepository.save(user);
      return modelAndView;

    } else {
      ModelAndView modelAndView = new ModelAndView("register");
      String errorMessage = "The username field is empty";
      user.setUsername("");
      modelAndView.addObject("error", errorMessage);
      return modelAndView;
    }
  }
}

