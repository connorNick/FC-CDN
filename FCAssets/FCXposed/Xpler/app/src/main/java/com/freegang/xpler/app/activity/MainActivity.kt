package com.freegang.xpler.app.activity

import android.os.Bundle
import android.view.LayoutInflater
import com.freegang.xpler.R
import com.freegang.xpler.app.base.BaseViewBindingActivity
import com.freegang.xpler.databinding.ActivityMainBinding

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {

    override fun onCreateViewBinding(inflater: LayoutInflater, savedInstanceState: Bundle?) =
        ActivityMainBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /// 不应该手动调用, 模块Hook时由模块调用, 用作提示模块是否加载成功
    private fun hookHint() {
        binding.moduleHint.text = getString(R.string.app_module_success)
    }
}
