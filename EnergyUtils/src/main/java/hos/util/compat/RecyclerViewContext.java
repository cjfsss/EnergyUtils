package hos.util.compat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>Title: RecyclerViewUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/3/18 17:41
 */
public class RecyclerViewContext {

    public static void notifyItemRangeChangedChecked(
            @NonNull final RecyclerView recyclerView,
            int adapterPosition, int size
    ) {
        if (recyclerView.isComputingLayout()) {
            // 延时递归处理。
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    notifyItemRangeChangedChecked(recyclerView, adapterPosition, size);
                }
            }, 100);
        } else {
            @SuppressWarnings("rawtypes") RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null) {
                adapter.notifyItemRangeChanged(adapterPosition, size);
            }
        }
    }


    /**
     * 连级更新选中项
     */
    public static void notifyChangedChecked(@NonNull RecyclerView recyclerView) {
        if (recyclerView.isComputingLayout()) {
            // 延时递归处理。
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    notifyChangedChecked(recyclerView);
                }
            }, 100);
        } else {
            @SuppressWarnings("rawtypes") RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 移除所有分割线
     */
    public static void removeAllItemDecoration(@NonNull RecyclerView recyclerView) {
        // 移除所有的分割线，这里要实现时间轴方式的展示效果
        int itemDecorationCount = recyclerView.getItemDecorationCount();
        for (int i = 0; i < itemDecorationCount; i++) {
            recyclerView.removeItemDecorationAt(i);
        }
    }
}
