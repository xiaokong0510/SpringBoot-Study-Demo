package com.xiao.utils;

import com.xiao.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Classname SignUtil
 * @Description 签名工具类
 * @Date 2020/10/30
 * @Author KongX
 * @version: 1.0.0
 */
@Slf4j
public class SignUtil {

    /**
     * 加密密钥
     */
    private final static String APP_KEY = "myKey123456";


    /**
     * 字符编码
     */
    private final static String INPUT_CHARSET = "UTF-8";

    /**
     * 超时时间,单位毫秒
     */
    private final static int TIME_OUT = 5 * 60 * 1000;


    /**
     * 生成要请求的签名参数数组
     *
     * @param sParaTemp 需要签名的参数
     * @return 要请求的签名参数数组
     */
    public static Map<String, String> sign(Map<String, String> sParaTemp) {
        //时间戳加入签名参数组中
        sParaTemp.put("timestamp", String.valueOf(System.currentTimeMillis()));
        //除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(sParaTemp);
        //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String presSr = createLinkString(sPara);
        //生成签名结果
        String mySign = DigestUtils.md5DigestAsHex((presSr + APP_KEY).getBytes()).toLowerCase();
        //签名结果加入请求提交参数组中
        sPara.put("sign", mySign);
        return sPara;
    }

    /**
     * 生成 sign
     *
     * @param sParaTemp         需要签名的参数map
     * @param currentTimeMillis 字符串格式的timestamp
     * @return 生成的sign
     */
    public static String createSign(Map<String, String> sParaTemp, String currentTimeMillis) {
        //当前时间戳加入签名参数组中
        sParaTemp.put("timestamp", currentTimeMillis);
        //除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(sParaTemp);
        //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String presSr = createLinkString(sPara);
        //返回生成签名结果
        return DigestUtils.md5DigestAsHex((presSr + APP_KEY).getBytes()).toLowerCase();
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    private static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<>(16);
        if (CollectionUtils.isEmpty(sArray)) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || "".equals(value) || "sign".equalsIgnoreCase(key)) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        return createLinkString(params, false);
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @param encode 是否需要UrlEncode
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params, boolean encode) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuilder preStr = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (encode) {
                value = urlEncode(value, INPUT_CHARSET);
            }
            //拼接时，不包括最后一个&字符
            if (i == keys.size() - 1) {
                preStr.append(key).append("=").append(value);
            } else {
                preStr.append(key).append("=").append(value).append("&");
            }
        }
        return preStr.toString();
    }


    /**
     * 验证签名
     *
     * @param params 通知返回来的参数数组
     * @return 验证结果
     */
    public static boolean verifySign(Map<String, String> params) {
        String sign = params.get("sign");
        String timestamp = params.get("timestamp");
        //过滤空值、sign
        Map<String, String> sParaNew = paraFilter(params);
        //获取待签名字符串
        String preSignStr = createLinkString(sParaNew);
        //获得签名验证结果
        String mySign = DigestUtils.md5DigestAsHex((preSignStr + APP_KEY).getBytes()).toLowerCase();
        if (mySign.equals(sign)) {
            //是否超时
            long curr = System.currentTimeMillis();
            return (curr - Long.parseLong(timestamp)) <= TIME_OUT;
        } else {
            return false;
        }
    }

    /**
     * URL转码
     *
     * @param content
     * @param charset
     * @return
     */
    private static String urlEncode(String content, String charset) {
        try {
            return URLEncoder.encode(content, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }


    public static void main(String[] args) {
        //构建参数map
        UserInfo userInfo = new UserInfo("zhangsan", 18);
        HashMap<String, String> stringMap = MapUtil.getNamValMap(userInfo);
        //获取当前时间戳
        String time = String.valueOf(System.currentTimeMillis());
        //参数map中添加key: timestamp
        stringMap.put("timestamp", time);
        //生成sign
        String sign = createSign(stringMap, time);
        log.info("-----生成的sign为：-----" + sign);
        stringMap.put("sign", sign);
        log.info("-----请求参数：-----" + stringMap);
        log.info("-----验签结果：-----" + verifySign(stringMap));
        log.info("----------------------------------------");
        stringMap.put("address","shenzhen");
        //直接使用sign方法，生成要请求的签名参数数组
        Map<String, String> signMap = sign(stringMap);
        log.info("-----添加签名后的请求参数：-----" + signMap);
        log.info("-----验签结果：-----" + verifySign(signMap));
    }
}
