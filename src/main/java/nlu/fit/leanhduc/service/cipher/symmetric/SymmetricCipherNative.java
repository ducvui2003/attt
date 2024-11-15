package nlu.fit.leanhduc.service.cipher.symmetric;

import nlu.fit.leanhduc.service.key.KeySymmetric;
import nlu.fit.leanhduc.util.CipherException;
import nlu.fit.leanhduc.util.FileUtil;
import nlu.fit.leanhduc.util.convert.Base64ConversionStrategy;
import nlu.fit.leanhduc.util.convert.ByteConversionStrategy;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SymmetricCipherNative extends AbsCipherNative<KeySymmetric> {
    SecretKey secretKey;
    Cipher cipher;
    IvParameterSpec iv;


    public SymmetricCipherNative() {

    }

    public SymmetricCipherNative(Algorithm algorithm) throws Exception {
        super(algorithm);
        this.cipher = Cipher.getInstance(algorithm.toString());
        if (algorithm.getIvSize() != 0) {
            iv = generateIV();
        }

    }

    public IvParameterSpec generateIV() {
        byte[] iv = new byte[algorithm.getIvSize()];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    @Override
    public void loadKey(KeySymmetric key) throws CipherException {
        this.secretKey = key.getSecretKey();
        this.iv = key.getIv();
    }

    @Override
    public KeySymmetric generateKey() {
        KeySymmetric key = new KeySymmetric();
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(this.algorithm.cipher);
            keyGenerator.init(algorithm.getKeySize());
            secretKey = keyGenerator.generateKey();
            IvParameterSpec iv = generateIV();
            key.setSecretKey(secretKey);
            key.setIv(iv);
            return key;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String encrypt(String data) throws Exception {
        if (algorithm.getIvSize() == 0)
            cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
        else
            cipher.init(Cipher.ENCRYPT_MODE, this.secretKey, this.iv);
        return this.conversionStrategy.convert(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decrypt(String encryptText) throws CipherException {
        try {
            byte[] data = this.conversionStrategy.convert(encryptText);
            if (algorithm.getIvSize() == 0)
                cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
            else
                cipher.init(Cipher.DECRYPT_MODE, this.secretKey, this.iv);
            return new String(cipher.doFinal(data), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new CipherException(e.getMessage());
        }
    }

    @Override
    public boolean encrypt(String src, String dest) throws CipherException {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
            BufferedInputStream bis = new BufferedInputStream((new FileInputStream(src)));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
            CipherInputStream cis = new CipherInputStream(bis, cipher);

// Read: doc tu file (input - read)
// Write: ghi vao file (output - write)
            byte[] read = new byte[1024];
            int i;
//            byte from bis -> bos
            while ((i = cis.read(read)) != -1) {
                bos.write(read, 0, i);
            }
//            cipher use bos to encrypt
            read = cipher.doFinal();
            if (read != null) {
                bos.write(read);
            }
            cis.close();
            bos.flush();
            bos.close();
            return true;
        } catch (InvalidKeyException | IOException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new CipherException(e.getMessage());
        }
    }

    @Override
    public boolean decrypt(String src, String dest) throws CipherException {
        try {
            cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
            BufferedInputStream bis = new BufferedInputStream((new FileInputStream(src)));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
            CipherOutputStream cos = new CipherOutputStream(bos, cipher);

            byte[] read = new byte[1024];
            int i;
            while ((i = bis.read(read)) != -1) {
                cos.write(read, 0, i);
            }
            read = cipher.doFinal();
            if (read != null) {
                cos.write(read);
            }
            bis.close();
            bos.flush();
            bos.close();
            return true;
        } catch (Exception e) {
            throw new CipherException(e.getMessage());
        }
    }

    @Override
    public boolean loadKey(String src) throws IOException {
        try {
            FileUtil.loadKeyFromFile(src, algorithm.cipher);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveKey(String dest) throws IOException {
        try {
            FileUtil.saveKeyToFile(secretKey, dest);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
