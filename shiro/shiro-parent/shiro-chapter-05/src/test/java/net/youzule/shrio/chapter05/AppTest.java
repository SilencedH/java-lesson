package net.youzule.shrio.chapter05;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class AppTest {

    @Test
    public void test1(){
        String string = "hello";

        String base64Encode = Base64.encodeToString(string.getBytes());
        System.out.println(base64Encode);

        String base64Decode = Base64.decodeToString(base64Encode);
        System.out.println(base64Decode);
    }

    @Test
    public void test2(){
        String string = "hello";

        String salt = "salt";
        String md5 = new Md5Hash(string,salt).toString();
        System.out.println(md5);
    }

    @Test
    public void test3(){
        String string = "hello";
        String salt = "salt";

        String sha1Hash = new Sha256Hash(string,salt).toString();
        System.out.println(sha1Hash);
    }


    @Test
    public void test4(){
        DefaultHashService defaultHashService = new DefaultHashService();
        defaultHashService.setHashAlgorithmName("SHA-512");
        defaultHashService.setPrivateSalt(new SimpleByteSource("123"));
        defaultHashService.setGeneratePublicSalt(true);

        defaultHashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        defaultHashService.setHashIterations(1);

        HashRequest hashRequest = new HashRequest.Builder()
                .setAlgorithmName("MD5")
                .setSource(ByteSource.Util.bytes("123"))
                .setSalt(ByteSource.Util.bytes("123"))
                .setIterations(2)
                .build();
        String hex = defaultHashService.computeHash(hashRequest).toHex();
        System.out.println(hex);
    }

    @Test
    public void test5(){
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        //生成key
        Key key = aesCipherService.generateNewKey();
        String test = "hello";
        //加密
        String encrptText = aesCipherService.encrypt(test.getBytes(),key.getEncoded()).toHex();
        System.out.println(encrptText);
        //解密
        String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText),key.getEncoded()).getBytes());
        System.out.println(text2);
    }

    @Test
    public void test6(){
        StringBuffer stringBuffer = new StringBuffer();
        Map<String,Object> map = new HashMap<String, Object>();
        stringBuffer.append("ss");
        map.put("stringBuffer",stringBuffer);
        System.out.println(map);
    }

    @Test
    public void test7(){
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        hashService.setHashIterations(1);
        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);
    }


    @Test
    public void test8(){
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("123".getBytes());

        System.out.println(randomNumberGenerator);

        String hex = randomNumberGenerator.nextBytes().toHex();
        System.out.println(hex);
    }

    @Test
    public void test9(){

    }
}