package org.springframework.social.quickstart;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Pen {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne
  @JoinColumn(name = "user", referencedColumnName = "id")
  private User user;

  private String write;
  private int minEntries;
  private double reward;

  private ArrayList<Brush> brushes;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "pen")
  public ArrayList<Brush> getBrushes() {
    return brushes;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public long getId() {
    return this.id;
  }

  public String getWrite() {
    return write;
  }

  public void setWrite(String write) {
    this.write = write;
  }

  public int getMinEntries() {
    return minEntries;
  }

  public void setMinEntries(int minEntries) {
    this.minEntries = minEntries;
  }

  public double getReward() {
    return reward;
  }

  public void setReward(double reward) {
    this.reward = reward;
  }
}
