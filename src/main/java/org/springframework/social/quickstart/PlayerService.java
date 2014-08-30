package org.springframework.social.quickstart;

import javax.inject.Inject;

/**
 * Created by pishty on 27/08/14.
 */
public class PlayerService {

  @Inject
  PlayerRepository repository;

  public Player save(Player player) {
    Player savedPlayer = repository.save(player);
    return savedPlayer;
  }
}
