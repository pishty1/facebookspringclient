package org.springframework.social.quickstart;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Brush {

  @Id
  private long id;

  @ManyToOne
  @JoinColumn(name = "user", referencedColumnName = "id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "pen", referencedColumnName = "id")
  private Pen pen;

  public Pen getPen() {
    return pen;
  }

  public void setPen(Pen pen) {
    this.pen = pen;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
