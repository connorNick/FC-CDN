package com.freegang.xpler.app.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingActivity<T : ViewBinding> : AppCompatActivity() {
    private var _binding: T? = null
    protected val binding get() = _binding!!

    abstract fun onCreateViewBinding(inflater: LayoutInflater, savedInstanceState: Bundle?): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = onCreateViewBinding(layoutInflater, savedInstanceState)
        setContentView(_binding!!.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}