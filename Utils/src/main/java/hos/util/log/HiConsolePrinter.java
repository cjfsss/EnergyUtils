package hos.util.log;

import android.util.Log;




public class HiConsolePrinter implements HiLogPrinter {

    @Override
    public void print( HiLogConfig config, int level, String tag,  String printString) {
        int len = printString.length();
        int countOfSub = len / HiLogConfig.MAX_LEN;
        if (countOfSub > 0) {
            StringBuilder log = new StringBuilder();
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                log.append(printString.substring(index, index + HiLogConfig.MAX_LEN));
                index += HiLogConfig.MAX_LEN;
            }
            if (index != len) {
                log.append(printString.substring(index, len));
            }
            Log.println(level, tag, log.toString());
        } else {
            Log.println(level, tag, printString);
        }
    }
}
