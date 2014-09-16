package org.springframework.social.quickstart;

import org.hibernate.Hibernate;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import io.lamma.Date;
import io.lamma.Dates;
import io.lamma.DayOfWeek;
import scala.collection.convert.Wrappers;

/**
 * Created by pishty on 26/08/14.
 */
public class TeamService {

  @Inject
  TeamRepository teamTepository;

  @Inject
  PlayerRepository playerRepository;

  @Inject
  GameRepository gameRepository;

  @Inject
  AvaRepository avaRepository;



  public Team save(Team team, FacebookProfile userProfile) {
    Team savedTeam = teamTepository.save(team);
    Player manager = new Player();
    manager.setName(userProfile.getName());
    manager.setFbId(userProfile.getId());
    manager.setTeam(savedTeam);
    playerRepository.save(manager);
    savedTeam.setManager(manager);
    Team saveTeam1 = teamTepository.save(savedTeam);
    return saveTeam1;
  }

  @Transactional
  public Team find(long id) {
    Team team = teamTepository.findOne(id);
    Hibernate.initialize(team.getPlayers());
    Hibernate.initialize(team.getGames());
    Collections.sort(team.getGames(), (Game g1, Game g2) -> g1.getDate().compareTo(g2.getDate()));
    return team;
  }

  public Team getTeamByPass(String pass) {
    Team team = teamTepository.findByPass(pass);
    return team;
  }

  @Transactional
  public Team joinPlayer(FacebookProfile fbProfile, Team team) {
    String message = "";
    Team teamByPass = teamTepository.findByPass(team.getTpass());
    Team one = teamTepository.findOne(teamByPass.getId());
    Hibernate.initialize(one.getPlayers());

    List<Player> players = one.getPlayers();
    boolean isInTeam = false;
    for (Player player : players) {
      if (player.getFbId().equals(fbProfile.getId())) {
        System.out.println("This player is in team already");

        message = "AlreadyJoined";
        isInTeam = true;
        break;
      }
    }
    if (!isInTeam) {
      System.out.println("This player is not in team already");
      message = "New";
      Player player = new Player();
      player.setFbId(fbProfile.getId());
      player.setName(fbProfile.getName());
      player.setTeam(one);
      playerRepository.save(player);
    }
    return one;
  }

  public Iterable<Team> findAll() {
    Iterable<Team> all = teamTepository.findAll();
    return all;
  }

  @Transactional
  public void createGames(ScheduleBean scheduleBean) {
    String time = scheduleBean.getTime();
    String[] split = time.split(":");
    int hour = Integer.valueOf(split[0]);
    int min = Integer.valueOf(split[1]);
    Team team = teamTepository.findOne(scheduleBean.getId());
    Hibernate.initialize(team.getGames());
    Date fromDate = Date.today();
    Date toDate = fromDate.$plus(scheduleBean.getNweeks() * 7);
    Dates allDates = Dates.from(fromDate).to(toDate).byWeek();
    ArrayList<Date> builtDates = new ArrayList<>();

    if (scheduleBean.isEveryMod()) {
      List<Date> bDates = allDates.on(DayOfWeek.MONDAY).build();
      builtDates = addDates(builtDates, bDates);
    }

    if (scheduleBean.isEveryTue()) {
      List<Date> bDates = allDates.on(DayOfWeek.TUESDAY).build();
      builtDates = addDates(builtDates, bDates);
    }

    if (scheduleBean.isEveryWed()) {
      List<Date> bDates = allDates.on(DayOfWeek.WEDNESDAY).build();
      builtDates = addDates(builtDates, bDates);
    }

    if (scheduleBean.isEveryThur()) {
      List<Date> bDates = allDates.on(DayOfWeek.THURSDAY).build();
      builtDates = addDates(builtDates, bDates);
    }

    if (scheduleBean.isEveryFri()) {
      List<Date> bDates = allDates.on(DayOfWeek.FRIDAY).build();
      builtDates = addDates(builtDates, bDates);
    }

    if (scheduleBean.isEverySat()) {
      List<Date> bDates = allDates.on(DayOfWeek.SATURDAY).build();
      builtDates = addDates(builtDates, bDates);
    }

    if (scheduleBean.isEverySun()) {
      List<Date> bDates = allDates.on(DayOfWeek.SUNDAY).build();
      builtDates = addDates(builtDates, bDates);
    }

    for (Date date : builtDates) {
      Game game = new Game();
      game.setTeam(team);
      Calendar calendar = new GregorianCalendar();
      calendar.set(date.yyyy(), date.mm(), date.dd(), hour, min);
      game.setDate(calendar.getTime());
      gameRepository.save(game);

    }
  }

  @Transactional
  public Game getGame(long id) {
    Game game = gameRepository.findOne(id);
    Hibernate.initialize(game.getAvailabilities());
    Hibernate.initialize(game.getTeam().getPlayers());
    System.out.println("game.getTeam().getPlayers().size() = " + game.getTeam().getPlayers().size());
    if (game.getAvailabilities().size() == 0) {
      System.out.println("inside if");
      List<Player> players = game.getTeam().getPlayers();
      for (Player player : players) {
        System.out.println("inside For");

        Availability availability = new Availability();
        availability.setGame(game);
        availability.setPlayer(player);
        availability.setStatus("Undefined");
        avaRepository.save(availability);
      }
      Game game2 = gameRepository.findOne(id);
      Hibernate.initialize(game2.getAvailabilities());
      System.out.println("game2.getAvailabilities().size() = " + game2.getAvailabilities().size());
    }
    System.out.println("game2.getAvailabilities().size() = " + game.getAvailabilities().size());
    return game;
  }

  private ArrayList<Date> addDates(ArrayList<Date> myDates, List<Date> newDates) {
    for (Date date : newDates) {
      myDates.add(date);
    }
    return myDates;
  }
}
