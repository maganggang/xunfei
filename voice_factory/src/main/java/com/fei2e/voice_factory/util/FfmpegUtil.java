package com.fei2e.voice_factory.util;

import com.fei2e.voice_factory.config.FfmpegConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FfmpegUtil
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/12 18:02
 * @Version 1.0
 **/
public class FfmpegUtil {
    public static Boolean processAudio(String pcmPath, String audioPath, FfmpegConfig ffmpegConfig) {
        List<String> commend = new ArrayList<String>();
        commend.add(ffmpegConfig.getPath());//win
        //commend.add("ffmpeg");//linux Docker
        commend.addAll(ffmpegConfig.getExecPre());
        commend.add("-i");
        commend.add(pcmPath);
//        commend.add("-acodec");//音频选项， 一般后面加copy表示拷贝
//        commend.add("mp3");
        commend.add("-y");//表示覆盖旧文件
        commend.add(audioPath+"."+ffmpegConfig.getExecVioce());
        //ffmpeg.exe -f s16le -ar 8000 -ac 1 -i audio.pcm out.wav
        try {
            Process process = new ProcessBuilder(commend).redirectErrorStream(true).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();// 加上这句，系统会等待转换完成。不加，就会在服务器后台自行转换。
            System.out.println("exitCode = "+exitCode);
            Boolean flag = exitCode==0?true:false;
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
