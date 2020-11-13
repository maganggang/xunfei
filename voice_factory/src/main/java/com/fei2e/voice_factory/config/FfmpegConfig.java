package com.fei2e.voice_factory.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName Ffmpeg
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/12 16:45
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "ffmpeg")
public class FfmpegConfig {
    private String path;
    private List<String>  execPre;
    private String execVioce;

    public List<String> getExecPre() {
        return execPre;
    }

    public void setExecPre(List<String> execPre) {
        this.execPre = execPre;
    }

    public String getExecVioce() {
        return execVioce;
    }

    public void setExecVioce(String execVioce) {
        this.execVioce = execVioce;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
