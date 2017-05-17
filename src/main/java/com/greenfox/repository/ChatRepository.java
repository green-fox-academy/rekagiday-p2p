package com.greenfox.repository;

import com.greenfox.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface ChatRepository extends CrudRepository<User, Long> {

}
