package hos.util.convert;

import java.io.IOException;

/**
 * <p>Title: ConvertException </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/3/2 20:59
 */
public class ConvertException extends IOException {

    private int statusCode;

    public ConvertException(int statusCode) {
        super();
        this.statusCode = statusCode;
    }

    public ConvertException(String detailMessage, int statusCode) {
        super(detailMessage);
        this.statusCode = statusCode;
    }

    public ConvertException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public ConvertException(Throwable cause, int statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    /**
     * Returns the unexpected Http response code
     *
     * @return response code
     */
    public int getStatusCode() {
        return statusCode;
    }
}
