package nlu.fit.leanhduc;


import nlu.fit.leanhduc.service.IAsymmetricEncrypt;
import nlu.fit.leanhduc.service.subsitutionCiper.SubstitutionEnglishCipher;
import nlu.fit.leanhduc.service.subsitutionCiper.SubstitutionVietnameseCipher;
import nlu.fit.leanhduc.util.CipherException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class SubstitutionCipherTest {

    IAsymmetricEncrypt<Map<Character, Character>> substitutionCipherVietnamese = new SubstitutionVietnameseCipher();
    IAsymmetricEncrypt<Map<Character, Character>> substitutionCipherEnglish = new SubstitutionEnglishCipher();
    Map<Character, Character> keyEnglish = Map.of('a', 'b', 'c', 'd');
    Map<Character, Character> keyVietnamese = Map.of('â', 'b', 'đ', 'd');

    @Test
    void testGenerateKey() {
        System.out.println(substitutionCipherVietnamese.generateKey());
        System.out.println(substitutionCipherEnglish.generateKey());
    }

    @Test
    void encryptEnglish() throws CipherException {
        String plainText = "abdz";
        substitutionCipherEnglish.loadKey(keyEnglish);
        String cipherText = substitutionCipherEnglish.encrypt(plainText);
        Assertions.assertEquals("bbdz", cipherText);
    }

    @Test
    void encryptVietnamese() throws CipherException {
        String plainText = "âđdz";
        substitutionCipherVietnamese.loadKey(keyVietnamese);
        String cipherText = substitutionCipherVietnamese.encrypt(plainText);
        Assertions.assertEquals("bddz", cipherText);
    }
}