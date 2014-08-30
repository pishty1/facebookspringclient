package org.springframework.social.quickstart;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by pishty on 27/08/14.
 */
@Entity
public class Player {

  @Id
  @GeneratedValue
  private long id;

  private String fbId;

  private String name;

  @ManyToOne
  private Team team;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public String getFbId() {
    return fbId;
  }

  public void setFbId(String fbId) {
    this.fbId = fbId;
  }
}
