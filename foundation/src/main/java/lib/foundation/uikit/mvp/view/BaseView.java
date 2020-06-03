package lib.foundation.uikit.mvp.view;

import androidx.lifecycle.Lifecycle;

public interface BaseView {

    Lifecycle getLifecycle();

    /**
     * 展示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void hideLoading();

    void showToast(String msg);

    /**
     * 错误处理
     * @param code
     * @param msg
     */
    void onError(String code, String msg);

}
