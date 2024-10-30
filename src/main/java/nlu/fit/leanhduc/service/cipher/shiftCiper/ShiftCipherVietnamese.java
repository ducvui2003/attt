package nlu.fit.leanhduc.service.cipher.shiftCiper;

import nlu.fit.leanhduc.util.alphabet.VietnameseAlphabetUtil;

public class ShiftCipherVietnamese extends ShiftCipher {
    public ShiftCipherVietnamese(Integer shift) {
        super(shift);
        this.alphabet = new VietnameseAlphabetUtil();
    }
}