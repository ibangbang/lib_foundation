package lib.foundation.uikit.base

import android.app.Application

abstract class AbstractBaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppContext.appInstance = this
    }

}