package org.springframework.social.quickstart.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.social.quickstart.model.Player;

/**
 * Created by pishty on 27/08/14.
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {

  @Query("select p from Player p where p.fbId= :fbid")
  Player findPlayerByFbId(@Param("fbid") String fbid);
}
