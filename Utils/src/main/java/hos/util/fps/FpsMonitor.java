package hos.util.fps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.text.DecimalFormat;

import hos.core.ActivityManager;
import hos.core.AppCompat;
import hos.util.log.HiLog;
import hos.util.utils.ResUtils;

/**
 * <p>Title: FpsMonitor </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/3 15:13
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class FpsMonitor {

    private FpsViewer fpsViewer = new FpsViewer();

    public void toggle() {
        fpsViewer.toggle();
    }

    public void listener(FpsCallback callback) {
        fpsViewer.addListener(callback);
    }

    public interface FpsCallback {
        void onFrame(Double fps);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private static class FpsViewer {
        private final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        private boolean isPlaying = false;

        private DecimalFormat decimal = new DecimalFormat("#.0 fps");
        private WindowManager windowManager = null;

        private TextView fpsView = null;

        private final FrameMonitor frameMonitor = new FrameMonitor();

        private FpsViewer() {
            init();
        }

        private TextView getFpsView() {
            if (fpsView != null) {
                return fpsView;
            }
            fpsView = new TextView(AppCompat.getApp());
            int dp2 = dp2px(2);
            fpsView.setPadding(dp2, dp2, dp2, dp2);
            fpsView.setBackgroundResource(android.R.color.darker_gray);
            fpsView.setTextColor(ResUtils.getColor(android.R.color.white));
            fpsView.setShadowLayer(4, 6, 6, Color.parseColor("#7000"));
            return fpsView;
        }

        void init() {
            windowManager = (WindowManager) AppCompat.getApp().getSystemService(Context.WINDOW_SERVICE);
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.flags =
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
                            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;//  /*不抢占焦点*/ or/*不可触摸*/ or /*不可拦截手势*/
            params.format = PixelFormat.TRANSLUCENT;
            params.gravity = Gravity.RIGHT | Gravity.TOP;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            } else {
                params.type = WindowManager.LayoutParams.TYPE_TOAST;
            }
            frameMonitor.addListener(new FpsCallback() {
                @Override
                public void onFrame(Double fps) {
                    if (fpsView != null) {
                        fpsView.setText(decimal.format(fps));
                    }
                }
            });
            ActivityManager.getInstance().addFrontBackCallback(new ActivityManager.FrontBackCallback() {
                @Override
                public void onChanged(Activity activity, boolean front) {
                    if (front) {
                        play();
                    } else {
                        stop();
                    }
                }
            });
        }

        private void stop() {
            frameMonitor.stop();
            if (isPlaying) {
                isPlaying = false;
                if (fpsView != null) {
                    windowManager.removeView(getFpsView());
                }
            }
        }

        private void play() {
            if (!hasOverlayPermission()) {
                startOverlaySettingActivity();
                HiLog.e("app has no overlay permission");
                return;
            }
            frameMonitor.start();
            if (!isPlaying) {
                isPlaying = true;
                windowManager.addView(getFpsView(), params);
            }
        }

        private void startOverlaySettingActivity() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                AppCompat.getApp().startActivity(
                        new Intent(
                                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + AppCompat.getApp().getPackageName())
                        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                );
            }
        }

        private boolean hasOverlayPermission() {
            return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(
                    AppCompat.getApp()
            );
        }

        public void toggle() {
            if (isPlaying) {
                stop();
            } else {
                play();
            }
        }

        private void addListener(FpsCallback callback) {
            frameMonitor.addListener(callback);
        }

        public int dp2px(float dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, AppCompat.getApp().getResources().getDisplayMetrics());
        }
    }
}
