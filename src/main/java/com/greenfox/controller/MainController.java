package com.greenfox.controller;

import com.greenfox.logging.RequestLogger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @Autowired
  RequestLogger requestLogger;

  @RequestMapping(value = "/")
  public String home(HttpServletRequest request) {
    try {
      requestLogger.info(request);
    } catch (Exception e) {
      requestLogger.error(request);
    }
    return "index";
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




}
