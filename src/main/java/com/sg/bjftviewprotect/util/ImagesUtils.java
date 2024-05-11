package com.sg.bjftviewprotect.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @ClassName ImagesUtils
 * @Author wangshuo
 * @Date 2024/5/9 10:05
 * @Version 1.0
 **/
public class ImagesUtils {

    /**
     * 根据文件路径转换Base64
     */
    public static String imageToBase64(String imagePath) {
        return imageToBase64(new File(imagePath));
    }

    /**
     * 根据文件转换Base64
     */
    public static String imageToBase64(File file) {
        String base64Image = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] imageBytes = new byte[(int) file.length()];
            fis.read(imageBytes);
            base64Image = "data:image/jpg;base64,"+ Base64.getEncoder().encodeToString(imageBytes);
            fis.close();
        } catch (IOException e) {
            System.out.println("Error converting image to Base64: " + e.getMessage());
        }
        return base64Image;
    }

    /**
     * 根据文件转换Base64
     */
    public static String imageToBase64(MultipartFile multipartFile){
        try {
            byte[] bytes = multipartFile.getBytes();
            return "data:image/jpg;base64,"+ Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
