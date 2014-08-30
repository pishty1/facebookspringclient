package org.springframework.social.quickstart;

import javax.inject.Inject;

public class UserService {

  @Inject
  UserRepository userRepository;

  public User saveUser(User user) {
    return userRepository.save(user);
  }

  public boolean userExists(String userId) {
    return userRepository.exists(userId);
  }

  public User getUser(String userId) {
    return userRepository.findOne(userId);
  }
}
