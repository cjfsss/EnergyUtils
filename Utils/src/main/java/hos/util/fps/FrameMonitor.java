package hos.util.fps;

import android.os.Build;
import android.view.Choreographer;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * <p>Title: FrameMonitor </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/3 15:11
 */
class FrameMonitor implements Choreographer.FrameCallback {
    private Choreographer choreographer = Choreographer.getInstance();
    private long frameStartTime = 0;//这个是记录 上一针到达的时间戳
    private long frameCount = 0;//1s 内确切绘制了多少帧
    private List<FpsMonitor.FpsCallback> listeners = new ArrayList<>();

    @Override
    public void doFrame(long frameTimeNanos) {
        long currentTimeMills = TimeUnit.NANOSECONDS.toMillis(frameTimeNanos);
        if (frameStartTime > 0) {
            //计算两针之间的 时间差
            // 500ms  100ms
            long timeSpan = currentTimeMills - frameStartTime;
            //fps 每秒多少帧  frame per second
            frameCount++;
            if (timeSpan > 1000) {
                double fps = frameCount * 1000 / (timeSpan * 1.0D);
//                HiLog.e("FrameMonitor", fps);
                if (listeners != null) {
                    for (FpsMonitor.FpsCallback listener : listeners) {
                        listener.onFrame(fps);
                    }
                }
                frameCount = 0;
                frameStartTime = currentTimeMills;
            }
        } else {
            frameStartTime = currentTimeMills;
        }
        start();
    }

    protected void start() {
        choreographer.postFrameCallback(this);
    }

    protected void stop() {
        frameStartTime = 0;
        listeners.clear();
        choreographer.removeFrameCallback(this);
    }

    protected void addListener(FpsMonitor.FpsCallback l) {
        listeners.add(l);
    }
}
