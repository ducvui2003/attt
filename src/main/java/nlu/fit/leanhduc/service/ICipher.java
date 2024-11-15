package nlu.fit.leanhduc.service;

import nlu.fit.leanhduc.util.CipherException;

import javax.crypto.NoSuchPaddingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Interface định nghĩa các phương thức cơ bản của một thuật toán mã hóa
 *
 * @param <T> kiểu dữ liệu của khóa
 */
public interface ICipher<T> {
    /**
     * Gán key cho thuật toán
     *
     * @param key khóa
     */
    void loadKey(T key) throws CipherException;

    /**
     * Tạo khóa cho thuật toán
     *
     * @return khóa
     */
    T generateKey();

    /**
     * Tải khóa từ src lên và gán cho thuật toán
     *
     * @param src đường dẫn file chứa khóa
     * @return true nếu tải thành công, ngược lại false
     */
    boolean loadKey(String src) throws IOException;

    /**
     * Lưu khá xuống file chỉ định
     *
     * @param dest đường dẫn lưu file khóa
     * @return true nếu lưu thành công, ngược lại false
     */
    boolean saveKey(String dest) throws IOException;

    /**
     * Mã hóa chuỗi
     *
     * @param plainText bản rõ
     * @return bản mã
     */
    String encrypt(String plainText) throws CipherException, Exception;

    /**
     * Mã hóa chuỗi
     *
     * @param encryptText bản mã
     * @return bản rõ
     */
    String decrypt(String encryptText) throws CipherException;

    /**
     * Mã hóa file
     *
     * @param src  file cần mã hóa
     * @param dest nơi lưu file cần mã hóa
     * @return bản rõ
     */
    default boolean encrypt(String src, String dest) throws CipherException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, FileNotFoundException {
        throw new UnsupportedOperationException();
    }

    /**
     * Giải hóa file
     *
     * @param src  file cần giải mã
     * @param dest nơi lưu file đã giải mã
     * @return bản rõ
     */
    default boolean decrypt(String src, String dest) throws CipherException {
        throw new UnsupportedOperationException();
    }
}
