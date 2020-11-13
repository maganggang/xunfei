package com.fei2e.voice_factory.controller;

import com.fei2e.voice_factory.biz.BaiDuBiz;
import com.fei2e.voice_factory.biz.XunFeiBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName XunFeiController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/12 16:53
 * @Version 1.0
 **/
@RestController
@Api(value = "讯飞语音",description ="讯飞语音" )
@RequestMapping("xunfei")
public class XunFeiController {
    @Autowired
    private XunFeiBiz xunFeiBiz;
    @PostMapping("/convert")
    @ResponseBody
    @ApiOperation(value = "文字转语音", notes="文字转语音")
    public String uploadFile(@RequestParam @ApiParam("文本信息") String message) {
        return xunFeiBiz.convertTextToVoice(message);
    }
}
