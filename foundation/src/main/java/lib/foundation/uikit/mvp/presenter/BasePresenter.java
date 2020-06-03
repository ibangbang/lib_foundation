package lib.foundation.uikit.mvp.presenter;


import lib.foundation.uikit.mvp.view.BaseView;

public interface BasePresenter<T extends BaseView> {

    /**
     * 绑定到view
     * @param t
     */
    void attachView(T t);

    /**
     * 取消绑定view
     */
    void detachView();

    /**
     * 是否绑定到视图
     * @return
     */
    boolean isAttached();

    /**
     * 获取视图
     * @return
     */
    T getView();
}