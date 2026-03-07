package com.diaryapp.diaryapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.diaryapp.diaryapp.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

}
