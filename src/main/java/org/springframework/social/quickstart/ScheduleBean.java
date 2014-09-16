package org.springframework.social.quickstart;

import java.util.List;

/**
 * Created by pishty on 03/09/14.
 */
public class ScheduleBean {

  private long id;
  private String time;
  private String label;
  private int nweeks;
  private boolean repeat;
  private boolean everyMod;
  private boolean everyTue;
  private boolean everyWed;
  private boolean everyThur;
  private boolean everyFri;
  private boolean everySat;
  private boolean everySun;

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public boolean isEveryMod() {
    return everyMod;
  }

  public void setEveryMod(boolean everyMod) {
    this.everyMod = everyMod;
  }

  public boolean isEveryTue() {
    return everyTue;
  }

  public void setEveryTue(boolean everyTue) {
    this.everyTue = everyTue;
  }

  public boolean isEveryWed() {
    return everyWed;
  }

  public void setEveryWed(boolean everyWed) {
    this.everyWed = everyWed;
  }

  public boolean isEveryThur() {
    return everyThur;
  }

  public void setEveryThur(boolean everyThur) {
    this.everyThur = everyThur;
  }

  public boolean isEveryFri() {
    return everyFri;
  }

  public void setEveryFri(boolean everyFri) {
    this.everyFri = everyFri;
  }

  public boolean isEverySat() {
    return everySat;
  }

  public void setEverySat(boolean everySat) {
    this.everySat = everySat;
  }

  public boolean isEverySun() {
    return everySun;
  }

  public void setEverySun(boolean everySun) {
    this.everySun = everySun;
  }

  public boolean isRepeat() {
    return repeat;
  }

  public void setRepeat(boolean repeat) {
    this.repeat = repeat;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public int getNweeks() {
    return nweeks;
  }

  public void setNweeks(int nweeks) {
    this.nweeks = nweeks;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
