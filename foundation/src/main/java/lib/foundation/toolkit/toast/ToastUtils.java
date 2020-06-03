package lib.foundation.toolkit.toast;

import android.content.Context;
import android.widget.Toast;

import lib.foundation.uikit.base.AppContext;

public class ToastUtils {

    public static void showShort(String msg) {
        Toast.makeText(AppContext.INSTANCE.getAppInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, int id) {
        Toast.makeText(AppContext.INSTANCE.getAppInstance(), context.getText(id), Toast.LENGTH_SHORT).show();
    }

}
