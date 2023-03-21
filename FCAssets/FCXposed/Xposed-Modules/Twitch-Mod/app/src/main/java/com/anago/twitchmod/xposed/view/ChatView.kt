package com.anago.twitchmod.xposed.view

import android.annotation.SuppressLint
import android.content.Context
import android.text.Spanned
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anago.twitchmod.xposed.hooks.HookApplication
import com.anago.twitchmod.xposed.view.adapter.ChatAdapter


class ChatView(private val context: Context) {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: ChatView? = null
            get() {
                if (field == null)
                    field = ChatView(HookApplication.mCurrentActivity!!)
                return field
            }
    }

    private val messages = ArrayList<Spanned>()
    private var playerView: FrameLayout? = null
    private var lastMessage: Spanned? = null

    @SuppressLint("ClickableViewAccessibility")
    private val recyclerView: RecyclerView = RecyclerView(context).apply {
        layoutManager = LinearLayoutManager(context)
        addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return true
            }
        })
        setOnTouchListener { _, _ -> false }
        suppressLayout(true)
        adapter = ChatAdapter(messages)
        layoutParams = RecyclerView.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
    }
    private val frameLayout: FrameLayout = FrameLayout(context).apply {
        addView(recyclerView)
    }

    fun setPlayerView(playerView: FrameLayout) {
        this.playerView = playerView
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addMessage(msg: Spanned) {
        if (playerView == null)
            return
        if (lastMessage == msg)
            return

        messages.add(msg)
        recyclerView.adapter!!.notifyItemInserted(messages.size - 1)
        recyclerView.smoothScrollToPosition(messages.size - 1)

        lastMessage = msg
    }

    fun getChatView(): FrameLayout {
        return frameLayout
    }

    fun Float.toDp(): Float {
        val metrics = context.resources.displayMetrics
        return this / metrics.density
    }
}