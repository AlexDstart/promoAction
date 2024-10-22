package com.example.promoaction.service;

import com.example.promoaction.entity.Prize;
import com.example.promoaction.repository.PrizeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PrizeService {
PrizeRepository prizeRepository;
public PrizeService(PrizeRepository prizeRepository)
{
    this.prizeRepository = prizeRepository;
}
public List<Prize> getAllPrizes()
{
    return prizeRepository.findAll();
}

public void add(Prize prize) {
    prizeRepository.save(prize);
}

public String saveImageToDirAndReturnPath(MultipartFile multipartFile){
    String name = multipartFile.getOriginalFilename();
    String pathDirImg = "C:\\Users\\AlexD\\IdeaProjects\\promoAction\\src\\main\\resources\\static\\img\\";

    Path path = Paths.get(pathDirImg + name);
    try {
        Files.write(path,multipartFile.getBytes());
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return "/img/"+name;


}



}
