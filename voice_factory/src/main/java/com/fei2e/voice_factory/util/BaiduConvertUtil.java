package com.fei2e.voice_factory.util;

import com.fei2e.voice_factory.config.BaiduConfig;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName BaiduConvertUtil
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/13 9:07
 * @Version 1.0
 **/
public class BaiduConvertUtil {
    private static String token;
    private static void getToken(BaiduConfig baiduConfig) throws Exception {
        String getTokenURL = baiduConfig.getTokenUrl()+"?grant_type=client_credentials" +
                "&client_id=" + baiduConfig.getApiKey() + "&client_secret=" + baiduConfig.getSecretKey();
        HttpURLConnection conn = (HttpURLConnection) new URL(getTokenURL).openConnection();
        token = new JSONObject(printResponse(conn)).getString("access_token");
    }

    public static String exexToMessage(BaiduConfig baiduConfig, File pcmFile) throws Exception {
        if(token==null|| StringUtils.isEmpty(token)) {
            getToken(baiduConfig);
        }
        HttpURLConnection conn = (HttpURLConnection) new URL(baiduConfig.getServerURL()).openConnection();
        // construct params
        JSONObject params = new JSONObject();
        params.put("format", "pcm");
        params.put("rate", 8000);
        params.put("channel", "1");
        params.put("token", token);
        params.put("cuid", "208522");
        params.put("len", pcmFile.length());
        params.put("speech", DatatypeConverter.printBase64Binary(loadFile(pcmFile)));

        // add request header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        conn.setDoInput(true);
        conn.setDoOutput(true);

        // send request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(params.toString());
        wr.flush();
        wr.close();

        return printResponse(conn);
    }

    private static String printResponse(HttpURLConnection conn) throws Exception {
        if (conn.getResponseCode() != 200) {
            // request error
            return "";
        }
        InputStream is = conn.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        System.out.println(new JSONObject(response.toString()).toString(4));
        return response.toString();
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            is.close();
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }
}
