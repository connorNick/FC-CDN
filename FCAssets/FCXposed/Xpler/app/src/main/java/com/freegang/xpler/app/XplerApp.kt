package com.freegang.xpler.app

import android.app.Application
import com.freegang.xpler.utils.log.KLogCat

class XplerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KLogCat.init(this)
        KLogCat.showTitle()
        KLogCat.showDivider()
        KLogCat.setTag("Xpler")
        KLogCat.openStorage()
    }
}