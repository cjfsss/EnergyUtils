package hos.util.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * <p>Title: NanoIdUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/6/20 13:49
 */
public class NanoIdUtils {
    public static final SecureRandom DEFAULT_NUMBER_GENERATOR = new SecureRandom();
    public static final char[] DEFAULT_ALPHABET = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public static final int DEFAULT_SIZE = 21;

    private NanoIdUtils() {
    }

    public static String randomNanoId() {
        return randomNanoId(DEFAULT_NUMBER_GENERATOR, DEFAULT_ALPHABET, DEFAULT_SIZE);
    }

    public static String randomNanoId(Random random, char[] alphabet, int size) {
        if (alphabet == null) {
            alphabet = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        }
        if (random == null) {
            random = new SecureRandom();
        }
        if (alphabet.length != 0 && alphabet.length < 256) {
            if (size <= 0) {
                throw new IllegalArgumentException("size must be greater than zero.");
            } else {
                int mask = (2 << (int) Math.floor(Math.log((double) (alphabet.length - 1)) / Math.log(2.0D))) - 1;
                int step = (int) Math.ceil(1.6D * (double) mask * (double) size / (double) alphabet.length);
                StringBuilder idBuilder = new StringBuilder();

                while (true) {
                    byte[] bytes = new byte[step];
                    random.nextBytes(bytes);

                    for (int i = 0; i < step; ++i) {
                        int alphabetIndex = bytes[i] & mask;
                        if (alphabetIndex < alphabet.length) {
                            idBuilder.append(alphabet[alphabetIndex]);
                            if (idBuilder.length() == size) {
                                return idBuilder.toString();
                            }
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("alphabet must contain between 1 and 255 symbols.");
        }
    }

}
