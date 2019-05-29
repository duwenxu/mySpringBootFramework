package com.example.SpringBoot.DataBase_CRUD.web;

import com.example.SpringBoot.DataBase_CRUD.web.text2voiceUtil.KTPublicUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@RestController
@Scope("prototype")
public class CoventController {
    public String TEXT2AUDIO_URL = "http://tsn.baidu.com/text2audio";

    //Rest Api
    @RequestMapping("/rest")
    public ModelAndView speechRestApi(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView res = new ModelAndView("index");
        //获取access_token
        String token= KTPublicUtil.INSTANCE.getAuth();
        // String token = PublicUtil.getAuth();
        //语音合成
         text2Audio("百度语音合成，我觉得妞妞说话最可爱！", token, "1", KTPublicUtil.INSTANCE.getRandomStringByLength(50),"5","5","5","4");
        //text2Audio("百度语音合成，我觉得妞妞说话最可爱！", token, "1", PublicUtil.getRandomStringByLength(50), "5", "5", "5", "4");
        return res;
    }

    /**
     * 所有参数方法
     *
     * @param tex  必填	合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
     * @param lan  必填	语言选择,填写zh
     * @param tok  必填	开放平台获取到的开发者access_token
     * @param ctp  必填	客户端类型选择，web端填写1
     * @param cuid 必填	用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内
     * @param spd  选填	语速，取值0-9，默认为5中语速
     * @param pit  选填	音调，取值0-9，默认为5中语调
     * @param vol  选填	音量，取值0-9，默认为5中音量
     * @param per  选填	发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
     * @Title text2Audio
     */
    public void text2Audio(String tex, String tok, String ctp, String cuid, String spd, String pit, String vol, String per) throws Exception {
        String params = "tex=" + URLEncoder.encode(tex, "UTF-8")
                + "&lan=zh&cuid=" + cuid + "&ctp=1&tok=" + tok + "&spd=" + spd
                + "&pit=" + pit + "&vol=" + vol + "&per=" + per;
        System.out.println(params);
        String data = KTPublicUtil.INSTANCE.postVoice(TEXT2AUDIO_URL, params);
        // String data = PublicUtil.postVoice(TEXT2AUDIO_URL,params);
        System.out.println("文件保存路径:" + data);
    }

    /**
     * 必填参数方法
     *
     * @param tex  必填	合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
     * @param lan  必填	语言选择,填写zh
     * @param tok  必填	开放平台获取到的开发者access_token
     * @param ctp  必填	客户端类型选择，web端填写1
     * @param cuid 必填	用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内
     * @Title text2Audio
     */
    public void text2Audio(String tex, String tok, String ctp, String cuid) throws Exception {
        String params = "tex=" + URLEncoder.encode(tex, "UTF-8")
                + "&lan=zh&cuid=" + cuid + "&ctp=1&tok=" + tok;
        System.out.println(params);
        String data = KTPublicUtil.INSTANCE.postVoice(TEXT2AUDIO_URL, params);
        //String data = PublicUtil.postVoice(TEXT2AUDIO_URL,params);
        System.out.println("文件保存路径:" + data);
    }
}
