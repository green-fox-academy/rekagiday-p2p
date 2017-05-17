package com.greenfox.controller;

import com.greenfox.logging.RequestLogger;
import com.greenfox.model.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  @Autowired
  RequestLogger requestLogger;


  @GetMapping(value = "/login")
  public User home(HttpServletRequest request) {
    try {
      requestLogger.info(request);
    } catch (Exception e) {
      requestLogger.error(request);
    }
    return new User();
  }

  @GetMapping(value = "/register")
  public User register() {
    return new User();
  }





}
