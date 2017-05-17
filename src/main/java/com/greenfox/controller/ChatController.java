package com.greenfox.controller;

import com.greenfox.logging.RequestLogger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  @Autowired
  RequestLogger requestLogger;


  @RequestMapping(value = "/")
  public String home(HttpServletRequest request) {
    String welcomeMessage = "";
    try {
      welcomeMessage = "Peer to Peer chat program";
      requestLogger.info(request);
    } catch (Exception e) {
      requestLogger.error(request);
    }
    return welcomeMessage;
  }
}
