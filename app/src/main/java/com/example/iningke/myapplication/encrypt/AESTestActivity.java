package com.example.iningke.myapplication.encrypt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.hxy_baseproject.utils.LogUtils;
import com.example.iningke.myapplication.R;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 测试 AES 加密
 *
 * @author hxy
 * @since 2018/8/23 20:52
 */
public class AESTestActivity extends AppCompatActivity {

    private SecretKey xavier;
    private byte[] encrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aestest);
        initView();
    }

    private EditText editText;

    private void initView() {
        editText = (EditText) findViewById(R.id.editText);
        try {
            xavier = generateKey("xavier");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void encrypt(View view) {
        String s = editText.getText().toString();
        try {
            encrypt = encrypt(s, xavier);
            LogUtils.e("加密后:" + new String(encrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decrypt(View view) {
        try {
            byte[] decrypt = decrypt(encrypt, xavier);
            LogUtils.e("解密后：" + new String(decrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生产秘钥
     *
     * @param seed 种子
     * @return the secretKey
     * @throws Exception
     */
    public SecretKey generateKey(String seed) throws Exception {
        // 获取秘钥生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        // 通过种子初始化
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(seed.getBytes("UTF-8"));
        //AES-128
        keyGenerator.init(128, secureRandom);
        // 生成秘钥并返回
        return keyGenerator.generateKey();
    }

    /**
     * 用秘钥进行加密
     *
     * @param content   明文
     * @param secretKey 秘钥
     * @return byte数组的密文
     * @throws Exception
     */
    public byte[] encrypt(String content, SecretKey secretKey) throws Exception {
        // 秘钥
        byte[] enCodeFormat = secretKey.getEncoded();
        return encrypt(content, enCodeFormat);
    }

    /**
     * 用秘钥进行加密
     *
     * @param content          明文
     * @param secretKeyEncoded 秘钥Encoded
     * @return byte数组的密文
     * @throws Exception
     */
    public byte[] encrypt(String content, byte[] secretKeyEncoded) throws Exception {
        // 创建AES秘钥
        SecretKeySpec key = new SecretKeySpec(secretKeyEncoded, "AES");
        // 创建密码器
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // Cipher cipher = Cipher.getInstance("AES");
        // 初始化加密器
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        // 加密
        return cipher.doFinal(content.getBytes("UTF-8"));
    }

    /**
     * 解密
     *
     * @param content          密文
     * @param secretKeyEncoded 秘钥
     * @return 解密后的明文
     * @throws Exception
     */
    public static byte[] decrypt(byte[] content, byte[] secretKeyEncoded) throws Exception {
        // 创建AES秘钥
        SecretKeySpec key = new SecretKeySpec(secretKeyEncoded, "AES");
        // 创建密码器
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // 初始化解密器
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        // 解密
        return cipher.doFinal(content);
    }

    /**
     * 解密
     *
     * @param content   密文
     * @param secretKey 秘钥
     * @return 解密后的明文
     * @throws Exception
     */
    public static byte[] decrypt(byte[] content, SecretKey secretKey) throws Exception {
        // 秘钥
        byte[] enCodeFormat = secretKey.getEncoded();
        return decrypt(content, enCodeFormat);
    }

}
