package com.fei2e.voice_factory.controller;

import com.fei2e.voice_factory.config.BaiduConfig;
import com.fei2e.voice_factory.util.BaiduConvertUtil;
import com.fei2e.voice_factory.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @ClassName BaiduController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/12 16:20
 * @Version 1.0
 **/
@RestController
@Api(value = "百度语音",description ="百度语音" )
@RequestMapping("baidu")
public class BaiduController {
    @Autowired
    private BaiduConfig baiduConfig;
    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public  String hello(){
        return  baiduConfig.getApiKey();
    }
    @ApiOperation(value = "语音转文字",notes = "语音转文字")
    @RequestMapping(value = "getMessage",method = RequestMethod.POST)
    public String voiceChangeToMessage(@RequestParam("file") MultipartFile file){
        String fieldName = file.getOriginalFilename();
        if(fieldName!=null&& StringUtils.endsWith(fieldName, ".pcm")) {
            File fileTemp=FileUtils.transferToFile(file);
            if(fileTemp.exists()){
                try {
                    String message=BaiduConvertUtil.exexToMessage(baiduConfig,fileTemp);
                    fileTemp.delete();
                    return message;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }
}
