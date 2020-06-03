package lib.foundation.uikit.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import lib.foundation.toolkit.toast.ToastUtils
import lib.foundation.uikit.mvp.view.BaseView

abstract class AbstractBaseFragment : Fragment(), BaseView {

    private val TAG = "AbstractBaseFragment"

    open var rootView: View? = null

    fun init(){}
    abstract fun initView()
    abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(getLayoutId(), container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun <T> find(id: Int): T {
        return rootView!!.findViewById<View>(id) as T
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> return onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    open fun onBackPressed(): Boolean {
        return false
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showToast(msg: String) {
        ToastUtils.showShort(msg)
    }

    override fun onError(code: String, msg: String) {
        Logger.e(String.format("ERROR:[%s:%s]", code, msg))
    }

}
