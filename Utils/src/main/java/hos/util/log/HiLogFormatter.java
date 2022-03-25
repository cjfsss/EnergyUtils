package hos.util.log;

public interface HiLogFormatter<T> {

    String format(T data);
}