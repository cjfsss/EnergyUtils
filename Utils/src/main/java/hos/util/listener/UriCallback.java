package hos.util.listener;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.io.File;

/**
 * <p>Title: UriForFileCallback </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/3/20 20:25
 */
public interface UriCallback {

    Uri getUriForFile(@NonNull Context context, @NonNull File file);

}
