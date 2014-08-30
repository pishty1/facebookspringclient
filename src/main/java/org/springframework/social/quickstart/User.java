package org.springframework.social.quickstart;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

  @Id
  private String id;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Pen> pens = new ArrayList<Pen>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Brush> brushes = new ArrayList<Brush>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<Pen> getPens() {
    return pens;
  }

  public List<Brush> getBrushes() {
    return brushes;
  }
}
