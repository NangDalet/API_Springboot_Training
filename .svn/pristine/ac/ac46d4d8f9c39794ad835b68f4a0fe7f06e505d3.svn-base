package com.ut.masterCode.helper;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs.provider.url.UrlFileName;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class FileUploadUtils {

  private static final String ROOT;
  private static final String PROJECT_NAME;
  private static final String FOLDER_UPLOAD;

  static {
    ROOT = System.getProperty("catalina.base");
    PROJECT_NAME = "logs/UTEMRMobileAPi";
    FOLDER_UPLOAD = "upload";
  }

  private FileUploadUtils() {

  }

  public static String saveFileUploaded(MultipartFile fileUploaded, String PROJECT_NAME) {
    try {
      byte[] bytes = IOUtils.toByteArray(fileUploaded.getInputStream());

      File path = new File(ROOT + File.separator + PROJECT_NAME + File.separator + FOLDER_UPLOAD);
      if (!path.exists()) path.mkdirs();

      String filename = fileUploaded.getOriginalFilename();
      assert filename != null;
      String ext = filename.substring(filename.lastIndexOf(".") + 1);
      String aliasFilename = UUID.randomUUID().toString();
      String filePath = path + File.separator + aliasFilename + FilenameUtils.EXTENSION_SEPARATOR + ext;
      System.out.println("filePath " + filePath);


      File file = new File(filePath);
      file.createNewFile();
      FileOutputStream fos = new FileOutputStream(file);
      fos.write(bytes);
      fos.close();

      return makeFileUploadedUrl(aliasFilename + FilenameUtils.EXTENSION_SEPARATOR + ext);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "";
  }

  public static String makeFileUploadedUrl(String filename) {
    return UrlFileName.SEPARATOR_CHAR + FOLDER_UPLOAD + UrlFileName.SEPARATOR_CHAR + filename;
  }

  public static void deleteFile(String fileName) {
    if(fileName != null && !fileName.isEmpty()){
      File file = new File(ROOT + File.separator + PROJECT_NAME + File.separator + fileName);
      file.delete();
    }
  }

  public static String saveFileBase64(String base64) {
    try {
      // split -> data:image/jpeg;base64
      String[] strings = base64.split(",");
      byte[] bytes = Base64.getDecoder().decode(strings[1]);

      File path = new File(ROOT + File.separator + PROJECT_NAME + File.separator + FOLDER_UPLOAD);
      System.out.println("path upload image base 64"+path);
      if (!path.exists()) path.mkdirs();

      String filename = "base64.png";
      String ext = filename.substring(filename.lastIndexOf(".") + 1);
      String aliasFilename = UUID.randomUUID().toString();
      String filePath = path + File.separator + aliasFilename + FilenameUtils.EXTENSION_SEPARATOR + ext;

      File file = new File(filePath);
      file.createNewFile();
      FileOutputStream fos = new FileOutputStream(file);
      fos.write(bytes);
      fos.close();

      return makeFileUploadedUrl(aliasFilename + FilenameUtils.EXTENSION_SEPARATOR + ext);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "";
  }

}