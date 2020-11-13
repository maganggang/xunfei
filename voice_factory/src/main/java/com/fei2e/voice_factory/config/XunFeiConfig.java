package com.fei2e.voice_factory.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName XunFeiConfig
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/12 16:37
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "xunfei.vop")
public class XunFeiConfig {
    @Value("${appid}")
    private String appId;
    @Value("${voice_name}")
    private String voiceName;
    private long speed;
    private long volume;
    private long pitch;
    private String savePath;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVoiceName() {
        return voiceName;
    }

    public void setVoiceName(String voiceName) {
        this.voiceName = voiceName;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public long getPitch() {
        return pitch;
    }

    public void setPitch(long pitch) {
        this.pitch = pitch;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
