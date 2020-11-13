package com.fei2e.voice_factory.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName FileUtils
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/13 9:17
 * @Version 1.0
 **/
public class FileUtils {
    public static File transferToFile(MultipartFile multipartFile) {
//        选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename= StringUtils.split(originalFilename,".");
            if(filename.length<=3){
                filename[0]+=new Date().getTime();
            }
            //String[] filename = originalFilename.split(".");
            file=File.createTempFile(filename[0], "."+filename[1]);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
