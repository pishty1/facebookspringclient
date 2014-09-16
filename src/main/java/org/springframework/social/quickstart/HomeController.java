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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import jdk.nashorn.internal.ir.RuntimeNode;

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

  private User user;

  @Inject
  public HomeController(Facebook facebook, TeamService teamService) {
    this.facebook = facebook;
    this.teamService = teamService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Model model) {
    List<Reference> friends = facebook.friendOperations().getFriends();
    FacebookProfile userProfile = facebook.userOperations().getUserProfile();

    /*if (userService.userExists(userProfile.getId())) {
      System.out.println("the user exist");
      userService.getUser(userProfile.getId());
    } else {
      System.out.println("the user doesnt exist");
      user = new User();
      user.setId(userProfile.getId());
      userService.saveUser(user);
    }*/

//    System.out.println("firends size is " + friends.size() + " user id is =" + user.getId());
//		int counter = 0;
//		for (Reference reference : friends) {
//			FacebookProfile userProfile = facebook.userOperations().getUserProfile(reference.getId());
//			String email = userProfile.getEmail();
//			System.out.println(counter++ + ") email is ====== " +email);
//		}
    model.addAttribute("friends", friends);
    return "home";
  }

  @RequestMapping(value = "/whosinthen", method = RequestMethod.GET)
  public String bidCoins(Model model) {
    return "bidHome";
  }

  @RequestMapping(value = "/teams/create", method = RequestMethod.GET)
  public String createTeam(Model model) {
    Team team = new Team();
    model.addAttribute("Team", team);
    return "createteam";
  }

  @RequestMapping(value = "/teams/schedule/{id}", method = RequestMethod.GET)
  public String scheduleGames(@PathVariable("id") long id, Model model) {
    Team team = teamService.find(id);
    ScheduleBean scheduleBean = new ScheduleBean();
    scheduleBean.setId(id);
    model.addAttribute("schedBean", scheduleBean);
    model.addAttribute("team", team);
    return "scheduling";
  }


  @RequestMapping(value = "/teams/schedule/create", method = RequestMethod.POST)
  public String createSchedule(@ModelAttribute("schedBean") ScheduleBean scheduleBean, Model model) {
    System.out.println("scheduleBean.isEveryMod() = " + scheduleBean.isEveryMod());
    teamService.createGames(scheduleBean);
    return "redirect:/teams/"  + scheduleBean.getId() + "/games";
  }

  @RequestMapping(value = "/teams/{id}/games", method = RequestMethod.GET)
  public String getGames(@PathVariable("id") long id, Model model) {
    Team team = teamService.find(id);
    model.addAttribute("team", team);
    return "games";
  }


  @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
  public String getGame(@PathVariable("id") long id, Model model) {
    Game game = teamService.getGame(id);
    model.addAttribute("game", game);
    return "showgame";
  }

  @RequestMapping(value = "/teams/enterpass", method = RequestMethod.GET)
  public String enterPassTeam(Model model) {
    Team team = new Team();
    model.addAttribute("Team", team);
    return "enterpass";
  }

  @RequestMapping(value = "/teams/get", method = RequestMethod.POST)
  public String joinTeam(@ModelAttribute("Team") Team myTeam, Model model) {
    FacebookProfile userProfile = facebook.userOperations().getUserProfile();
    System.out.println("this is myTeam = " + myTeam.getTpass());
    Team team = teamService.joinPlayer(userProfile, myTeam);

    return "redirect:/teams/" + team.getId();
  }

  @RequestMapping(value = "/teams/add", method = RequestMethod.POST)
  public String addTeam(@ModelAttribute("Team") Team team) {
    System.out.println("team.getName() = " + team.getName());
    System.out.println("team.getTpass() = " + team.getTpass());

    Team savedteam = teamService.save(team, facebook.userOperations().getUserProfile());
    return "redirect:/teams/" + savedteam.getId();
  }

  @RequestMapping(value = "teams/{id}", method = RequestMethod.GET)
  public String getTeam(@PathVariable("id") long id, Model model) {
    Team team = teamService.find(id);
    for (Player player : team.getPlayers()) {
      System.out.println("player = " + player.getName());
    }

    model.addAttribute("team", team);
    return "showteam";
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String home1(Model model) {
    List<Reference> friends = facebook.friendOperations().getFriends();
    model.addAttribute("friends", friends);
    return "home";
  }

}
