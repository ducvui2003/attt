package nlu.fit.leanhduc.service.cipher.hillCipher;

import nlu.fit.leanhduc.util.alphabet.EnglishAlphabetUtil;

public class HillCipherVietnamese extends HillCipher {
    public HillCipherVietnamese() {
        super();
        this.alphabetUtil = new EnglishAlphabetUtil();
    }
}
