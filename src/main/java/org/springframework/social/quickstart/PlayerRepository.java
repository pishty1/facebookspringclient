package org.springframework.social.quickstart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by pishty on 27/08/14.
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {

  @Query("select p from Player p where p.fbId= :fbid")
  Player findPlayerByFbId(@Param("fbid") String fbid);
}
