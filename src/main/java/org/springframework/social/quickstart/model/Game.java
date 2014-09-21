package org.springframework.social.quickstart.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.social.quickstart.TeamService;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Created by pishty on 31/08/14.
 */
@Entity
public class Game {

  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  private Team team;

  @OneToMany(mappedBy = "game" )
  private List<Availability> availabilities;


  @DateTimeFormat(pattern =  "dd/MM/YY HH:mm")
  private Date date;

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public int getAvailablePlayers() {
    int count = 0;
    for (Availability availability : availabilities) {
      if(availability.getStatus().equals(TeamService.THERE)){
        count ++;
      }
    }
    return count;
  }

  public String getFd() {
    return TeamService.simpleDateFormat.format(this.date);
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public List<Availability> getAvailabilities() {
    return availabilities;
  }

  public void setAvailabilities(List<Availability> availabilities) {
    this.availabilities = availabilities;
  }
}
