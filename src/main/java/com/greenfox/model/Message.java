package com.greenfox.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "messages")
public class Message {

  @Id
  @GeneratedValue (strategy = GenerationType.AUTO)
  int id;
  String username;
  String text;
  long timestamp;

  public int getId() {
    return id;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setId(int id) {
    this.id = id;
  }
}
