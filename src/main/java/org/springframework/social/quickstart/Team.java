package org.springframework.social.quickstart;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Created by pishty on 26/08/14.
 */
@Entity
public class Team {

  @Id
  @GeneratedValue
  private long id;

  private String name;

  private String tpass;

  private int teamType;

  @OneToOne
  private Player manager;

  @OneToMany(mappedBy = "team")
  private List<Game> games;

  @OneToMany(mappedBy = "team")
  private List<Player> players;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTpass() {
    return tpass;
  }

  public void setTpass(String tpass) {
    this.tpass = tpass;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getTeamType() {
    return teamType;
  }

  public void setTeamType(int teamType) {
    this.teamType = teamType;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public Player getManager() {
    return manager;
  }

  public void setManager(Player manager) {
    this.manager = manager;
  }

  public List<Game> getGames() {
    return games;
  }

  public void setGames(List<Game> games) {
    this.games = games;
  }
}
