package org.springframework.social.quickstart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import sun.security.krb5.internal.crypto.crc32;

import java.util.List;

/**
 * Created by pishty on 26/08/14.
 */
public interface TeamRepository extends CrudRepository<Team, Long> {

  @Query("select t from Team t where t.tpass = :pass")
  Team findByPass(@Param("pass") String pass);

  @Query("select t from Team t where t.manager.fbId = :fbId")
  List<Team> findTeamsByManagerId(@Param("fbId") String fbId);
}
