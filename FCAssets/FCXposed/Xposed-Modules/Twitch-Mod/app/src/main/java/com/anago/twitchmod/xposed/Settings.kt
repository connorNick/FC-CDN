package com.anago.twitchmod.xposed

import android.app.AlertDialog
import android.app.AndroidAppHelper
import android.content.Context
import android.content.SharedPreferences
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.Toast
import com.anago.twitchmod.R
import com.anago.twitchmod.xposed.hooks.HookApplication.mCurrentActivity

data class SettingData(
    val title: String,
    val key: String,
    val defValue: Boolean
)

object Settings {
    private val sharedPreferences: SharedPreferences =
        AndroidAppHelper.currentApplication().getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    fun getBoolean(settingData: SettingData): Boolean {
        return getBoolean(settingData.key, settingData.defValue)
    }

    val HIDE_FLOATING_CHAT = SettingData("Hide Floating Chat", "hide_floating_chat", false)
    val AUTO_CLAIM_POINTS = SettingData(ModuleManager.getString(R.string.auto_claim_points), "auto_claim_points", true)
    val AD_BLOCK = SettingData(ModuleManager.getString(R.string.ad_block), "ad_block", true)

    val SETTINGS = arrayOf(HIDE_FLOATING_CHAT, AUTO_CLAIM_POINTS, AD_BLOCK)

    fun showSettingsDialog() {
        if (mCurrentActivity == null || mCurrentActivity!!.isFinishing) return

        val dialog = AlertDialog.Builder(mCurrentActivity!!)
        dialog.setTitle(ModuleManager.getString(R.string.settings))

        val layout = LinearLayout(mCurrentActivity!!).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(10, 0, 10, 0)
            for (setting in SETTINGS) {
                val switch = Switch(mCurrentActivity!!).apply { isChecked = getBoolean(setting.key, setting.defValue) }
                switch.text = setting.title
                addView(switch)
            }
        }
        dialog.setView(layout)

        dialog.setPositiveButton(ModuleManager.getString(R.string.save)) { _, _ ->
            for (i in 0..layout.childCount) {
                val switch = layout.getChildAt(i) as? Switch ?: continue
                val setting = SETTINGS[i]
                sharedPreferences.edit().putBoolean(setting.key, switch.isChecked).apply()
            }
            restartMessage()
        }
        dialog.setNegativeButton(ModuleManager.getString(R.string.cancel), null)
        dialog.show()
    }

    private fun restartMessage() {
        Toast.makeText(mCurrentActivity!!, ModuleManager.getString(R.string.restart_settings_message), Toast.LENGTH_SHORT).show()
    }
}
