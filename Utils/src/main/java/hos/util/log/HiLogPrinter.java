package hos.util.log;



public interface HiLogPrinter {
    void print( HiLogConfig config, int level, String tag,  String printString);
}
