package org.springframework.social.quickstart;

import org.hibernate.Hibernate;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.quickstart.model.Availability;
import org.springframework.social.quickstart.model.Game;
import org.springframework.social.quickstart.model.Manager;
import org.springframework.social.quickstart.model.Player;
import org.springframework.social.quickstart.model.Team;
import org.springframework.social.quickstart.repos.AvaRepository;
import org.springframework.social.quickstart.repos.GameRepository;
import org.springframework.social.quickstart.repos.ManagerRepository;
import org.springframework.social.quickstart.repos.PlayerRepository;
import org.springframework.social.quickstart.repos.TeamRepository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import io.lamma.Date;
import io.lamma.Dates;
import io.lamma.DayOfWeek;

/**
 * Created by pishty on 26/08/14.
 */
public class TeamService {

  @Inject
  private TeamRepository teamTepository;

  @Inject
  private PlayerRepository playerRepository;

  @Inject
  private GameRepository gameRepository;

  @Inject
  private AvaRepository avaRepository;

  @Inject
  private ManagerRepository managerRepository;

  public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");

  public static final String UNDEFINED = "Undefined";
  public static final String NOTSURE = "Not sure";
  public static final String THERE = "Will Be there";
  public static final String CANT = "Cant make it";


  public Team save(Team team, FacebookProfile userProfile) {
    Team savedTeam = teamTepository.save(team);
    Manager manager = new Manager();
    manager.setName(userProfile.getName());
    manager.setFbId(userProfile.getId());
    manager.getTeams().add(savedTeam);
    managerRepository.save(manager);
    savedTeam.setManager(manager);
    Team saveTeam1 = teamTepository.save(savedTeam);
    return saveTeam1;
  }

  @Transactional
  public Team find(long id) {
    Team team = teamTepository.findOne(id);
    Hibernate.initialize(team.getPlayers());
    Hibernate.initialize(team.getGames());
    team.getGames().forEach((x) -> Hibernate.initialize(x.getAvailabilities()));
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
      Player player = playerRepository.findPlayerByFbId(fbProfile.getId());
      if(player != null) {
        player.getTeam().add(one);
      } else {
        player = new Player();
        player.setFbId(fbProfile.getId());
        player.setName(fbProfile.getName());
        player.getTeam().add(one);
      }
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
      builtDates.addAll(bDates);
    }

    if (scheduleBean.isEveryTue()) {
      List<Date> bDates = allDates.on(DayOfWeek.TUESDAY).build();
      builtDates.addAll(bDates);
    }

    if (scheduleBean.isEveryWed()) {
      List<Date> bDates = allDates.on(DayOfWeek.WEDNESDAY).build();
      builtDates.addAll(bDates);
    }

    if (scheduleBean.isEveryThur()) {
      List<Date> bDates = allDates.on(DayOfWeek.THURSDAY).build();
      builtDates.addAll(bDates);
    }

    if (scheduleBean.isEveryFri()) {
      List<Date> bDates = allDates.on(DayOfWeek.FRIDAY).build();
      builtDates.addAll(bDates);
    }

    if (scheduleBean.isEverySat()) {
      List<Date> bDates = allDates.on(DayOfWeek.SATURDAY).build();
      builtDates.addAll(bDates);
    }

    if (scheduleBean.isEverySun()) {
      List<Date> bDates = allDates.on(DayOfWeek.SUNDAY).build();
      builtDates.addAll(bDates);
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

  public Game saveGame(Game game) {
    return gameRepository.save(game);
  }

  @Transactional
  public Game findGameWithAvailability(long id) {
    Game game = gameRepository.findOne(id);
    Hibernate.initialize(game.getAvailabilities());
    Hibernate.initialize(game.getTeam().getPlayers());
    System.out.println("game.getTeam().getPlayers().size() = " + game.getTeam().getPlayers().size());
    if (game.getAvailabilities().size() == 0) {
      List<Player> players = game.getTeam().getPlayers();
      for (Player player : players) {
        Availability availability = new Availability();
        availability.setGame(game);
        availability.setPlayer(player);
        availability.setStatus("Undefined");
        avaRepository.save(availability);
        game.getAvailabilities().add(availability);
      }
    }
    return game;
  }


  @Transactional
  public Game getGame(long id) {
    Game game = gameRepository.findOne(id);
    return game;
  }

  public Availability saveAvailability(Availability availability) {
    Availability one = avaRepository.findOne(availability.getId());
    one.setStatus(availability.getStatus());
    Availability save = avaRepository.save(one);
    System.out.println("saved = " + save.getStatus());
    return save;
  }

  public List<Team> findTeamsByManager(String fbId) {
    List<Team> teams = teamTepository.findTeamsByManagerId(fbId);
    return teams;
  }

  @Transactional
  public Player getPlayerByFbId(String id) {
    Player player = playerRepository.findPlayerByFbId(id);
    Hibernate.initialize(player.getTeam());
    List<Team> teams = player.getTeam();
    for(Team team: teams) {
      Hibernate.initialize(team.getGames());
      List<Game> games = team.getGames();
      for(Game game : games) {
        Hibernate.initialize(game.getAvailablePlayers());
      }
    }
    return player;
  }
}
