/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.quickstart;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.quickstart.model.Availability;
import org.springframework.social.quickstart.model.Game;
import org.springframework.social.quickstart.model.Player;
import org.springframework.social.quickstart.model.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Simple little @Controller that invokes Facebook and renders the result. The injected {@link Facebook} reference is configured with the required authorization
 * credentials for the current user behind the scenes.
 *
 * @author Keith Donald
 */
@Controller
public class HomeController {


  private final Facebook facebook;
  private final TeamService teamService;

  @Inject
  public HomeController(Facebook facebook, TeamService teamService) {
    this.facebook = facebook;
    this.teamService = teamService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Model model) {
    /*Model<Reference> friends = facebook.friendOperations().getFriends();
    model.addAttribute("friends", friends);*/
    return "whoshome";
  }

  @RequestMapping(value = "/whosinthen", method = RequestMethod.GET)
  public String bidCoins(Model model) {
    return "bidHome";
  }

  @RequestMapping(value = "/manager/teams/create", method = RequestMethod.GET)
  public String createTeam(Model model) {
    Team team = new Team();
    model.addAttribute("Team", team);
    return "createteam";
  }

  @RequestMapping(value = "/manager/teams/{id}/schedule", method = RequestMethod.GET)
  public String scheduleGames(@PathVariable("id") long id, Model model) {
    Team team = teamService.find(id);
    ScheduleBean scheduleBean = new ScheduleBean();
    scheduleBean.setId(id);
    model.addAttribute("schedBean", scheduleBean);
    model.addAttribute("team", team);
    return "scheduling";
  }


  @RequestMapping(value = "/manager/teams/schedule/create", method = RequestMethod.POST)
  public String createSchedule(@ModelAttribute("schedBean") ScheduleBean scheduleBean, Model model) {
    System.out.println("scheduleBean.isEveryMod() = " + scheduleBean.isEveryMod());
    teamService.createGames(scheduleBean);
    return "redirect:/manager/teams/"  + scheduleBean.getId() + "/games";
  }

  @RequestMapping(value = "/manager/teams/{id}/games", method = RequestMethod.GET)
  public String getGames(@PathVariable("id") long id, Model model) {
    Team team = teamService.find(id);
    model.addAttribute("team", team);
    return "games";
  }

  @RequestMapping(value = "/manager/teams/add", method = RequestMethod.POST)
  public String addTeam(@ModelAttribute("Team") Team team) {
    Team savedteam = teamService.save(team, facebook.userOperations().getUserProfile());
    return "redirect:/manager/teams/" + savedteam.getId();
  }

  @RequestMapping(value = "/manager/games/{id}", method = RequestMethod.GET)
  public String getGame(@PathVariable("id") long id, Model model) {
    Game game = teamService.findGameWithAvailability(id);
    Availability availability = new Availability();
    List<String> statuses = new ArrayList<>();
    statuses.add(TeamService.UNDEFINED);
    statuses.add(TeamService.NOTSURE);
    statuses.add(TeamService.THERE);
    statuses.add(TeamService.CANT);
    model.addAttribute("availability", availability);
    model.addAttribute("statuses", statuses);
    model.addAttribute("fbId", facebook.userOperations().getUserProfile().getId());
    model.addAttribute("game", game);
    return "showgame";
  }

  @RequestMapping(value = "/player/games/{id}", method = RequestMethod.GET)
  public String getGameForPlayer(@PathVariable("id") long id, Model model) {
    Game game = teamService.findGameWithAvailability(id);
    Availability availability = new Availability();
    List<String> statuses = new ArrayList<>();
    statuses.add(TeamService.UNDEFINED);
    statuses.add(TeamService.NOTSURE);
    statuses.add(TeamService.THERE);
    statuses.add(TeamService.CANT);
    model.addAttribute("availability", availability);
    model.addAttribute("statuses", statuses);
    model.addAttribute("fbId", facebook.userOperations().getUserProfile().getId());
    model.addAttribute("game", game);
    return "showplayeravailability";
  }

  @RequestMapping(value = "/manager/games/{id}/edit", method = RequestMethod.GET)
  public String findGame(@PathVariable("id") long id, Model model) {
    Game game = teamService.getGame(id);
    model.addAttribute("game", game);
    return "editgame";
  }

  @RequestMapping(value = "/manager/games/{id}/editgame", method = RequestMethod.POST)
  public String editGame(@ModelAttribute("game") Game game, Model model) {
    teamService.saveGame(game);
    return "redirect:/manager/teams/"+ game.getTeam().getId() +"/games";
  }

  @RequestMapping(value = "/manager/imanage", method = RequestMethod.GET)
  public String imanage(Model model) {
    String fbId = facebook.userOperations().getUserProfile().getId();
    List<Team> teams = teamService.findTeamsByManager(fbId);
    model.addAttribute("teams", teams);
    return "showteams";
  }

  @RequestMapping(value = "/manager/teams/{id}", method = RequestMethod.GET)
  public String getTeam(@PathVariable("id") long id, Model model) {
    Team team = teamService.find(id);
    model.addAttribute("team", team);
    return "showteam";
  }

  @RequestMapping(value = "/player/games", method = RequestMethod.GET)
  public String getAvailabilities(Model model) {
    Player player = teamService.getPlayerByFbId(facebook.userOperations().getUserProfile().getId());
    model.addAttribute("player", player);
    List<Team> teams = player.getTeam();
    List<Game> games = new ArrayList<>();
    teams.forEach((team)->games.addAll(team.getGames()));
    Collections.sort(games, (x1, x2)-> x1.getDate().compareTo(x2.getDate()));
    model.addAttribute("games", games);
    return "showplayergames";
  }

  @RequestMapping(value = "/player/teams/join", method = RequestMethod.POST)
  public String joinTeam(@ModelAttribute("Team") Team myTeam, Model model) {
    FacebookProfile userProfile = facebook.userOperations().getUserProfile();
    Team team = teamService.joinPlayer(userProfile, myTeam);
    return "redirect:/player/games";
  }

  @RequestMapping(value = "/player/teams/enterpass", method = RequestMethod.GET)
  public String enterPassTeam(Model model) {
    Team team = new Team();
    model.addAttribute("Team", team);
    return "enterpass";
  }

  @RequestMapping(value = "/player/games/{id}/ava", method = RequestMethod.POST)
  public String editAva(@ModelAttribute("availability") Availability availability, @PathVariable("id") long id, Model model) {
    Availability savedAvailability = teamService.saveAvailability(availability);
    return "redirect:/player/games/" + id;
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String home1(Model model) {
    List<Reference> friends = facebook.friendOperations().getFriends();
    model.addAttribute("friends", friends);
    return "home";
  }

}
