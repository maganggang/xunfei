package com.fei2e.voice_factory.biz;

import com.fei2e.voice_factory.config.FfmpegConfig;
import com.fei2e.voice_factory.config.XunFeiConfig;
import com.fei2e.voice_factory.util.Constant;
import com.fei2e.voice_factory.util.FfmpegUtil;
import com.fei2e.voice_factory.util.XunFeiConverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Calendar;

/**
 * @ClassName XunFeiBiz
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/12 17:14
 * @Version 1.0
 **/
@Service
public class XunFeiBiz {
    @Value("${web.upload-path}")
    private String path;
    @Autowired
    private XunFeiConfig xunFeiConfig;
    @Autowired
    private FfmpegConfig ffmpeg;

    public String convertTextToVoice(String message) {
        Calendar c = Calendar.getInstance();
        String audioName = String.valueOf(c.getTimeInMillis())+ Math.round(Math.random() * 100000);
        //讯飞转PCM
        String pcmPath = path+ Constant.SF_FILE_SEPARATOR+audioName+".pcm";
        XunFeiConverUtil.convert(message,pcmPath,xunFeiConfig);
        //由于生成PCM是异步的，这里while一直等待，直到生成
        File file = new File(pcmPath);
        String result=null;
        //转码
        while(true) {
            if (file.exists()) {
                //PCM转MP3
                String audioPath = path + Constant.SF_FILE_SEPARATOR + audioName;
                Boolean flag = FfmpegUtil.processAudio(pcmPath, audioPath, ffmpeg);
                if(flag){
                    return audioPath+".wav";
                }
                break;
            }
        }
        return result;
    }
}
