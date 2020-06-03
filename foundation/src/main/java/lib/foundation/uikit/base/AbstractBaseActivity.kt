package lib.foundation.uikit.base

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import lib.foundation.toolkit.toast.ToastUtils
import lib.foundation.uikit.mvp.view.BaseView

abstract class AbstractBaseActivity : AppCompatActivity() , BaseView{

    private val TAG = "AbstractBaseActivity"

    abstract fun getLayoutId(): Int

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doSomeBeforeSetContentView(savedInstanceState)
        setContentView(getLayoutId())
        //        StatusBarUtil.setLightMode(this);
        setLightStatusBarForM(this@AbstractBaseActivity, isLightStatusBar())
        init()
    }

    fun doSomeBeforeSetContentView(savedInstanceState: Bundle?) {
        initArguments(savedInstanceState)
    }

    /**
     * 是否亮暗模式
     */
    fun isLightStatusBar(): Boolean {
        return false
    }

    /**
     * Android6.0设置亮色状态栏模式
     */
    @TargetApi(Build.VERSION_CODES.M)
    private fun setLightStatusBarForM(activity: Activity, dark: Boolean) {
        val window = activity.window
        if (window != null) {
            val decor = window.decorView
            var ui = decor.systemUiVisibility
            if (dark) {
                ui = ui or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                ui = ui and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
            decor.systemUiVisibility = ui
        }
    }

    open fun initArguments(savedInstanceState: Bundle?) {}

    fun replaceFragment(id: Int, fragment: AbstractBaseFragment) {
        currentFrag = fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(id, fragment).commitAllowingStateLoss()
    }

    internal var currentFrag: AbstractBaseFragment? = null

    fun getCurrentFrag(): AbstractBaseFragment? {
        return currentFrag
    }

    fun switchFragment(id: Int, from: AbstractBaseFragment, to: AbstractBaseFragment) {
        if (currentFrag === to) {
            return
        }
        currentFrag = to
        val transaction = supportFragmentManager.beginTransaction()
        if (!to.isAdded) {
            transaction.hide(from).add(id, to).commitAllowingStateLoss()
        } else {
            transaction.hide(from).show(to).commitAllowingStateLoss()
        }
    }

    fun switchFragment(id: Int, to: AbstractBaseFragment) {
        if (currentFrag == null) {
            replaceFragment(id, to)
        } else {
            switchFragment(id, currentFrag!!, to)
        }
    }

    fun isNeedSwitch(to: AbstractBaseFragment): Boolean {
        return currentFrag !== to
    }

    fun getContext(): Context {
        return this
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showToast(msg: String) {
        ToastUtils.showShort(msg)
    }

    override fun onError(code: String, msg: String) {
        Logger.e(String.format("ERROR:[%s:%s]", code, msg))
    }

    fun <T> find(id: Int): T {
        return findViewById<View>(id) as T
    }

}