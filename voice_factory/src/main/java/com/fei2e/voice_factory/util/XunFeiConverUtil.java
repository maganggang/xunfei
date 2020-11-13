package com.fei2e.voice_factory.util;

import com.fei2e.voice_factory.config.XunFeiConfig;
import com.iflytek.cloud.speech.*;

/**
 * @ClassName XunFeiConverUtil
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/12 17:21
 * @Version 1.0
 **/
public class XunFeiConverUtil {
    public static Boolean flag = true;
    //合成监听器
    static SynthesizeToUriListener synthesizeToUriListener = new SynthesizeToUriListener() {
        //progress为合成进度0~100
        public void onBufferProgress(int progress) {}
        //会话合成完成回调接口
        //uri为合成保存地址，error为错误信息，为null时表示合成会话成功
        public void onSynthesizeCompleted(String uri, SpeechError error) {
            if(error==null){
                System.out.println("chuxian:"+uri);
            }else{
                System.out.println(error);
            }
            flag = true;
        }
        public void onEvent(int arg0, int arg1, int arg2, int arg3, Object arg4, Object arg5) {
            // TODO Auto-generated method stub

        }
    };
    public static void convert(String message,String path,XunFeiConfig xunFeiConfig){
        try {
            //System.out.println(message);
            SpeechUtility.createUtility(SpeechConstant.APPID + "="+xunFeiConfig.getAppId());
            //Setting.setShowLog(true);
            SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
            mTts.setParameter(SpeechConstant.VOICE_NAME, xunFeiConfig.getVoiceName());//
            mTts.setParameter(SpeechConstant.SPEED, xunFeiConfig.getSpeed()+"");//
            mTts.setParameter(SpeechConstant.VOLUME, xunFeiConfig.getVolume()+"");//
            mTts.setParameter("LIB_NAME_64", "lib_name_64");
            mTts.synthesizeToUri(message,path,synthesizeToUriListener);
        } catch (Exception e) {
            System.out.println("出错");
            e.printStackTrace();
        }
    }
}
