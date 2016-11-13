package ch.ocram.linkbasket.main

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.config.FlowConfig

class LinkBasketApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FlowManager.init(FlowConfig.Builder(this).build())
    }
}