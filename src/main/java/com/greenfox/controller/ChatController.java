package com.greenfox.controller;

import com.greenfox.logging.RequestLogger;
import com.greenfox.model.User;
import com.greenfox.repository.ChatRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  @Autowired
  RequestLogger requestLogger;

  @Autowired
  ChatRepository repo;


  @GetMapping(value = "/login")
  public void home(HttpServletRequest request) {
    try {
      requestLogger.info(request);
    } catch (Exception e) {
      requestLogger.error(request);
    }
  }
  
  @PostMapping(value = "/register")
  public User saveUser(HttpServletRequest request, String username) {
    try {
      requestLogger.info(request);
    } catch (Exception e) {
      requestLogger.error(request);
    }
    User user = new User();
    user.setUsername(username);
    return repo.save(user);
  }





}
