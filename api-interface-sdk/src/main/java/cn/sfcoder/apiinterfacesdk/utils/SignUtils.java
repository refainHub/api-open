package cn.sfcoder.apiinterfacesdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @Author: refain
 * @Description:
 * @Date: 2024/2/17 22:08
 * @Version: 1.0
 */
public class SignUtils {
    public static String genSign(String body, String secretKey){
        Digester md5=new Digester(DigestAlgorithm.SHA256);
        String content=body+"."+secretKey;
        return md5.digestHex(content);
    }
}
