package nlu.fit.leanhduc.util.constraint;

/**
 * Enum {@code Size}
 * <p>
 * Enum định nghĩa các kích thước hỗ trợ
 * </p>
 */
public enum Size {
    Size_1(8, 1),
    Size_2(16, 2),
    Size_4(32, 4),
    Size_5(40, 5),
    Size_7(56, 7),
    Size_8(64, 8),
    Size_12(96, 12),
    Size_14(112, 14),
    Size_16(128, 16),
    Size_21(168, 21),
    Size_24(192, 24),
    Size_32(256, 32),
    Size_40(320, 40),
    Size_56(448, 56),
    Size_64(512, 64),
    Size_128(1024, 128),
    Size_256(2048, 256),
    Size_512(4096, 512),
    Size_0(0, 0),
    SIZE_384(3072, 384);

    private int bit;
    private int byteFormat;

    Size(int bit, int byteFormat) {
        this.bit = bit;
        this.byteFormat = byteFormat;
    }

    public int getBit() {
        return bit;
    }

    public int getByteFormat() {
        return byteFormat;
    }


    @Override
    public String toString() {
        return String.valueOf(bit);
    }

    public static Size valueOfBit(int bit) {
        for (Size size : values()) {
            if (size.bit == bit) {
                return size;
            }
        }
        return Size_0;
    }

    public static Size byteFormat(int byteFormat) {
        for (Size size : values()) {
            if (size.byteFormat == byteFormat) {
                return size;
            }
        }
        return Size_0;
    }

}
