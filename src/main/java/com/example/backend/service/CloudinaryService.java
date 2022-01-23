package com.example.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

//    Cloudinary cloudinary;

    private Map<String,String> valuesMap = new HashMap<>();

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name","duell9lnz",
            "api_key","114213817868275",
            "api_secret","ZxkYqHGFaoUg6v3eR6_i_V3AwPs",
            "secure",true));


//    public CloudinaryService(){
//        valuesMap.put("cloud_name", "duell9lnz");
//        valuesMap.put("api_key", "114213817868275");
//        valuesMap.put("api_secret", "ZxkYqHGFaoUg6v3eR6_i_V3AwPs");
//        cloudinary = new Cloudinary(valuesMap);
//    }

    public Map upload(MultipartFile multipartFile) throws IOException{
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }

    public Map delete(String id) throws IOException{
        Map result= cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException{
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return file;
    }
}
