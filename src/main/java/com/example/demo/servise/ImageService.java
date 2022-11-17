package com.example.demo.servise;

import com.example.demo.entity.Image;
import com.example.demo.entity.dto.ImageDto;
import com.example.demo.excaption.BadRequest;
import com.example.demo.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageService {
    private final String fileUrl = "uploads/images/";

    @Value("${serverAddress}")
    private String serverAddress;

    private final ImageRepo imageRepo;

    public ImageService(ImageRepo iMageRepo) {
        this.imageRepo = iMageRepo;
    }

    public String create(MultipartFile file) {
        try {
            String YMD = getYMD();// year month day
            String type = Objects.requireNonNull(file.getContentType()).split("/")[1];
            String token = UUID.randomUUID().toString();

            String URL = YMD + "/" + token + "." + type;
            File folder = new File(fileUrl + YMD);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            Path path = Paths.get(fileUrl).resolve(URL);
            Files.copy(file.getInputStream(), path);
            return createImage(YMD,type,file.getSize(),token);

        } catch (IOException e) {
            throw new BadRequest("File not created");
        }
    }
    public String getYMD() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return String.format("%s/%s/%s", year, month + 1, day);
    }


    private String createImage(String ymd, String type, long size, String token) {
        Image image = new Image();
        image.setPath(ymd);
        image.setSize(size);
        image.setToken(token);
        image.setType(type);
        image.setUrl(serverAddress + "api/v1/image/get/" + token);
        image.setCreateAt(LocalDateTime.now());
        imageRepo.save(image);
        image = imageRepo.findByToken(token).get();
      return String.format("ImageId: %s\nURL: %s",image.getId(),image.getUrl());
    }

    private void convertImageToDto(Image image, ImageDto imageDto) {
        imageDto.setSize(image.getSize());
        imageDto.setToken(image.getToken());
    }


    public UrlResource load(String token) {
        try {
            Image entity = getImage(token);
            Path file = Paths.get(fileUrl).resolve(entity.getPath() + "/" + entity.getToken() + "." + entity.getType());
            UrlResource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Image getImage(String token) {
        return imageRepo.findByToken(token).orElseThrow(() -> new IllegalArgumentException("Image not found"));
    }

    public byte[] getImg(String token) {
        try {
            Image entity = getImage(token);
            String path = fileUrl + "/" + entity.getPath() + "/" + entity.getToken() + "." + entity.getType();

            byte[] imageInByte;
            BufferedImage originalImage;
            try {
                originalImage = ImageIO.read(new File(path));
            } catch (Exception e) {
                return new byte[0];
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(originalImage, "png", baos);

            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}
