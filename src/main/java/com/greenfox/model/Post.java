package com.greenfox.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Component
@Table (name = "messages")
public class Post {


  @Id
  @GeneratedValue (strategy = GenerationType.AUTO)
  int id;

}
