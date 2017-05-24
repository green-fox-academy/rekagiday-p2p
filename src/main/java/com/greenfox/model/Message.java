package com.greenfox.model;

import java.sql.Timestamp;
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
  Timestamp timestamp;

  public int getId() {
    return id;
  }

  public void setTimestamp(Timestamp timestamp) {
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

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Message{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", text='" + text + '\'' +
        ", timestamp=" + timestamp +
        '}';
  }
}
