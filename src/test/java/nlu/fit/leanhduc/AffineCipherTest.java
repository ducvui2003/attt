package nlu.fit.leanhduc;

import nlu.fit.leanhduc.service.cipher.classic.AffineCipher;
import nlu.fit.leanhduc.service.key.classic.AffineKeyClassic;
import nlu.fit.leanhduc.util.CipherException;
import nlu.fit.leanhduc.util.alphabet.EnglishAlphabetUtil;
import nlu.fit.leanhduc.util.alphabet.VietnameseAlphabetUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AffineCipherTest {
    private AffineCipher englishCipher;
    private AffineCipher vietnameseCipher;

    private AffineKeyClassic keys;


    @BeforeEach
    void setup() {
        englishCipher = new AffineCipher(new EnglishAlphabetUtil());
        vietnameseCipher = new AffineCipher(new VietnameseAlphabetUtil());

    }

    @Test
    public void testEncryptSimpleTextWithKey7_3() throws CipherException {
        // Plaintext: "WORLD", Key: a = 7, b = 3
        keys = new AffineKeyClassic(7, 3);
        englishCipher.loadKey(keys);

        String plaintext = "WORLD";
        String expectedCiphertext = "BXSCY";
        String encryptedText = englishCipher.encrypt(plaintext);

        Assertions.assertEquals(expectedCiphertext, encryptedText, "The encrypted text should match 'BXSGJ'");
    }

    @Test
    public void testEncryptLowercaseTextWithKey5_8() throws CipherException {
        // Plaintext: "hello", Key: a = 5, b = 8
        keys = new AffineKeyClassic(5, 8);
        englishCipher.loadKey(keys);

        String plaintext = "hello";
        String expectedCiphertext = "rclla";
        String encryptedText = englishCipher.encrypt(plaintext);

        Assertions.assertEquals(expectedCiphertext, encryptedText, "The encrypted text should match 'rclla'");
    }


    @Test
    public void testEncryptAndDecryptWithKey9_2() throws CipherException {
        // Plaintext: "affinecipher", Key: a = 9, b = 2
        keys = new AffineKeyClassic(9, 2);
        englishCipher.loadKey(keys);

        String plaintext = "affinecipher";
        String encryptedText = englishCipher.encrypt(plaintext);
        String decryptedText = englishCipher.decrypt(encryptedText);

        Assertions.assertEquals(plaintext, decryptedText, "Decrypting the encrypted text should return the original plaintext");
    }

    @Test
    public void testEncryptWithKey1_0ShouldBeIdentity() throws CipherException {
        // Plaintext: "identity", Key: a = 1, b = 0 (identity transformation)
        keys = new AffineKeyClassic(1, 0);
        englishCipher.loadKey(keys);

        String plaintext = "identity";
        String encryptedText = englishCipher.encrypt(plaintext);

        Assertions.assertEquals(plaintext, encryptedText, "With key (1, 0), the encrypted text should match the plaintext");
    }

    @Test
    public void testLoadAndSaveFile() {
        keys = new AffineKeyClassic(7, 3);
        try {
            this.englishCipher.loadKey(keys);
            englishCipher.saveKey("D:\\university\\ATTT\\security-tool\\src\\test\\resources\\affine\\key.txt");
            englishCipher.loadKey("D:\\university\\ATTT\\security-tool\\src\\test\\resources\\affine\\key.txt");
            System.out.println(this.keys.name());
            System.out.println(englishCipher.getKey().name());
        } catch (CipherException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
