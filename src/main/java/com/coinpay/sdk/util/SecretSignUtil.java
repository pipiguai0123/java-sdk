package com.coinpay.sdk.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SecretSignUtil {

    /**
     * 排序参数
     */
    public static String sortParam(Map<String, Object> paramValues) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> keys = new ArrayList<String>(paramValues.keySet());
        Collections.sort(keys);
        int i = 0;
        for (String key : keys) {
            stringBuilder.append(key).append("=").append(paramValues.get(key).toString());
            if (i != keys.size() - 1) {
                stringBuilder.append("&");
            }
            i++;
        }
        System.out.println("排序后的值：" + stringBuilder);
        return stringBuilder.toString();
    }

    /**
     * 排序参数
     */
    public static String sortParam(Object obj) {
        Map<String, Object> paramValues = BeanUtil.beanToMap(obj, false, true);
        return sortParam(paramValues);
    }

    /**
     * 私钥加密-base64
     */
    public static String signPriKey(String paramMap, String privateKey) {
        Sign sign = new Sign(SignAlgorithm.SHA1withRSA, privateKey, null);
        return Base64.encode(sign.sign(paramMap.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 公钥验签
     *
     * @param paramMap  参数
     * @param oldSign   签名
     * @param publicKey 公钥
     */
    public static boolean checkPubKey(String paramMap, String oldSign, String publicKey) {
        Sign sign = new Sign(SignAlgorithm.SHA1withRSA, null, publicKey);
        return sign.verify(paramMap.getBytes(StandardCharsets.UTF_8), Base64.decode(oldSign.getBytes(StandardCharsets.UTF_8)));
    }

    public static final String priv = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKNhH62Ds/VOfdGTkj+G7ii/zbn+QO/uZ5epFrEzbrrHP3O3Mxe2OvtOw72Oy3K4HbAtn4sO7qHmk1Zh87w8Y+rOTnjl0eQpM9OVCFbsFG+yMTceBJbO/I0K5CKTy7czawb3C6Ul4F1ksFcl9hmje64iQ17U06Icf3pMvzZUa9vdAgMBAAECgYADJtU922fOEGLQd2yPkaqH+jU9hIhbqIlyjUY9Cl01OLFCafMQHsmR/gG8JyRJFQCrnKGyhMn5MRHADZkuVSrnPYFabSsSF3gqeEMditdymojt1zbrQiVf4Tu9CZDRxoR3zgLKoImp3ZWiIJaNcANovtHzDfmy3RWN5OQ7WOF+mQJBAOCBAUFS4poVYwNjuuRKXKSK8EoelArVHCWxFZAWgKQZ4e7DFlnwaz5fMwUGwaijHzMEoh7EfjJ7TfsOvPtigqUCQQC6TNkwRDsb80VNCB+Bve+e7jyskAILWoOnkUF/3TzE06LIrnv/3j9ZmSJ2wUWKML3dvZ+eRAXwT2c0WmrYykbZAkAIYei5Fq4tUKUcyEl/IqOo5SMpsVCXwvNFGQKUGbL97ZV5A8G+4/ItJwLRqjnq4QRWNFMVUQLhQadcu2UlAb2dAkAVyrt1C+YmJNSsaBDXalJHMvLh9I5oqZPQ3ArfXA+prl/SPaa/jU23u1PutDjgK0dqUq4DI03WiFM4KoNpDHAxAkEArKObpKN8HuKp5x5QzCX0cktmCwDzXKK+QCDO63EpAHAb76zj0PrCirKm//tU68V2gPXU5RM+DsxON77PyAVFAw==";
    public static final String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjYR+tg7P1Tn3Rk5I/hu4ov825/kDv7meXqRaxM266xz9ztzMXtjr7TsO9jstyuB2wLZ+LDu6h5pNWYfO8PGPqzk545dHkKTPTlQhW7BRvsjE3HgSWzvyNCuQik8u3M2sG9wulJeBdZLBXJfYZo3uuIkNe1NOiHH96TL82VGvb3QIDAQAB";

    public static void main(String[] args) {

        RSA rsa=new RSA();
        System.out.println(rsa.getPrivateKeyBase64());
        System.out.println(rsa.getPublicKeyBase64());

        String test = "afaf";

        String key = signPriKey(test,priv);
        System.out.println(checkPubKey(test, key, pub));

    }


}
