package org.springframework.social.quickstart;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

public class FileService {

  @Inject
  Environment environment;


  public void saveFile(MultipartFile multifile, String imageId) throws IllegalStateException, IOException {
    String originalFilename = multifile.getOriginalFilename();

    String extension = "";

    int i = originalFilename.lastIndexOf('.');
    if (i > 0) {
      extension = originalFilename.substring(i + 1);
    }

    String imagePath = environment.getProperty("image.path") + "/" + imageId + "." + extension;
    File file = new File(imagePath);
    multifile.transferTo(file);
  }

}
