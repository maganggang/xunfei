package com.fei2e.voice_factory.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName BaiduConfig
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/12 16:24
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "baidu.vop")
public class BaiduConfig {
    private String tokenUrl;
    private String serverURL;
    private String token;
    private String apiKey;
    private String secretKey;
    private long cuid;

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getCuid() {
        return cuid;
    }

    public void setCuid(long cuid) {
        this.cuid = cuid;
    }
}
