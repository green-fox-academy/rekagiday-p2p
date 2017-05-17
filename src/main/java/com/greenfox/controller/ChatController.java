package com.greenfox.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

 @RequestMapping(value = "/")
  public String home() {
   return "Peer to Peer chat program";
 }

}
