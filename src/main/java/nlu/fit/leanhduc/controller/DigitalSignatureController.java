package nlu.fit.leanhduc.controller;

import nlu.fit.leanhduc.service.digital.DigitalSignature;
import nlu.fit.leanhduc.service.cipher.Algorithm;
import nlu.fit.leanhduc.service.key.KeySignature;
import nlu.fit.leanhduc.util.constraint.Hash;
import nlu.fit.leanhduc.util.constraint.Size;
import nlu.fit.leanhduc.util.convert.Base64ConversionStrategy;
import nlu.fit.leanhduc.util.convert.ByteConversionStrategy;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

public class DigitalSignatureController {
    private static DigitalSignatureController INSTANCE;

    private DigitalSignatureController() {
    }

    public static DigitalSignatureController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DigitalSignatureController();
        }
        return INSTANCE;
    }

    public Map<String, String> generateKey(Hash hashFunction, Size size) throws NoSuchAlgorithmException, NoSuchProviderException {
        DigitalSignature digitalSignature = new DigitalSignature(new Algorithm("DSA", size.getBit(), hashFunction.getHashWithDigitalSignature()));
        KeySignature keySignature = digitalSignature.generateKey();
        ByteConversionStrategy byteConversionStrategy = new Base64ConversionStrategy();
        return Map.of("public-key", byteConversionStrategy.convert(keySignature.getPublicKey().getEncoded()),
                "private-key", byteConversionStrategy.convert(keySignature.getPrivateKey().getEncoded()));
    }
}