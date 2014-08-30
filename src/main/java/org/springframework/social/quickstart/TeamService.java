package org.springframework.social.quickstart;

import org.hibernate.Hibernate;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by pishty on 26/08/14.
 */
public class TeamService {

  @Inject
  TeamRepository teamTepository;

  @Inject
  PlayerRepository playerRepository;

  public Team save(Team team) {
    teamTepository.save(team);
    return team;
  }

  public Team find(long id) {
    Team team = teamTepository.findOne(id);
    return team;
  }

  public Team getTeamByPass(String pass) {
    Team team = teamTepository.findByPass(pass);
    return team;
  }

  @Transactional
  public boolean isPlayerPartOfTeam(FacebookProfile fbProfile, Team team) {
    Team teamByPass = teamTepository.findByPass(team.getTpass());
    Team one = teamTepository.findOne(teamByPass.getId());
    System.out.println("thisi is theh she sdljfsdl teamByPass.getId() = " + one.getId());
    /*Team one = teamTepository.findOne(teamByPass.getId());
    System.out.println("one.getName() ++++++++======== " + one.getName());*/

    Hibernate.initialize(one.getPlayers());
    List<Player> players = one.getPlayers();
    boolean isInTeam = false;
    for (Player player : players) {
      if (player.getFbId().equals(fbProfile.getId())) {
        isInTeam = true;
        break;
      }
    }
    if (!isInTeam) {
      Player player = new Player();
      player.setFbId(fbProfile.getId());
      player.setName(fbProfile.getName());
      playerRepository.save(player);
    }
    return isInTeam;
  }

  public Iterable<Team> findAll() {
    Iterable<Team> all = teamTepository.findAll();
    return all;
  }
}
