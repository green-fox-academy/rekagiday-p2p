package com.greenfox.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table (name = "users")
public class User {

  String username;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;


  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public long getId() {
    return id;
  }
}
