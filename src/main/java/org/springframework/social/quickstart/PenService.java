package org.springframework.social.quickstart;

import java.util.List;

import javax.inject.Inject;

public class PenService {

  @Inject
  PenRepository penRepository;

  public Pen savePen(Pen pen) {
    Pen savedPen = penRepository.save(pen);
    return savedPen;
  }

  public List<Pen> getAllPens() {
    return (List<Pen>) penRepository.findAll();
  }

  public Pen getPen(long id) {
    return penRepository.findOne(id);
  }

}
