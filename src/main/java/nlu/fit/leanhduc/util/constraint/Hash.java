package nlu.fit.leanhduc.util.constraint;

public enum Hash {
    MD2("MD2"),
    MD5("MD5", "HmacMD5"),
    SHA_1("SHA-1", "HmacSHA1"),
    SHA_224("SHA-224", "HmacSHA224"),
    SHA_256("SHA-256", "HmacSHA256"),
    SHA_384("SHA-384"),
    SHA_512("SHA-512"),
    SHA_512_224("SHA-512/224", "HmacSHA512/224"),
    SHA_512_256("SHA-512/256", "HmacSHA512/256"),
    SHA3_224("SHA3-224", "HmacSHA3-224"),
    SHA3_256("SHA3-256", "HmacSHA3-256"),
    SHA3_384("SHA3-384", "HmacSHA3-384"),
    SHA3_512("SHA3-512", "HmacSHA3-512");

    private String value;
    private String hmacValue;

    Hash(String value) {
        this.value = value;
    }

    Hash(String value, String hmacValue) {
        this.value = value;
        this.hmacValue = hmacValue;
    }

    public String getHmacValue() {
        return hmacValue;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}