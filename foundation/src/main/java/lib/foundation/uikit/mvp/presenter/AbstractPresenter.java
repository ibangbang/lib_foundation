package lib.foundation.uikit.mvp.presenter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import lib.foundation.uikit.lifecycle.BaseLifecycleObserver;
import lib.foundation.uikit.mvp.view.BaseView;

public abstract class AbstractPresenter<T extends BaseView> implements BasePresenter<T>, BaseLifecycleObserver {

    private static final String TAG = "AbstractPresenter";

    public T view;

    public AbstractPresenter(T v) {
        attachView(v);
    }

    @Override
    public void attachView(T view) {
        this.view = view;
        view.getLifecycle().addObserver(this);
    }

    @Override
    public void detachView() {
        if (isAttached()) {
            view.hideLoading();
            view.getLifecycle().removeObserver(this);
        }
        this.view = null;
    }

    @Override
    public T getView() {
        return view;
    }

    /**
     * 获取context
     *
     * @return
     */
    public Context getContext() {
        if (isAttached()) {
            if (view instanceof Activity) {
                return (Context) view;
            }

            if (view instanceof Fragment) {
                return ((Fragment) view).getActivity();
            }
        }
        return null;
    }

    @Override
    public boolean isAttached() {
        return view != null;
    }

    protected void showLoading() {
        if (isAttached()) {
            view.showLoading();
        }
    }

    protected void hideLoading() {
        if (isAttached()) {
            view.hideLoading();
        }
    }

    @Override
    public void onAny(LifecycleOwner owner) {

    }

    @Override
    public void onCreate(LifecycleOwner owner) {

    }

    @Override
    public void onStart(LifecycleOwner owner) {

    }

    @Override
    public void onStop(LifecycleOwner owner) {

    }

    @Override
    public void onResume(LifecycleOwner owner) {

    }

    @Override
    public void onPause(LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        detachView();
    }

}
