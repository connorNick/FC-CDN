package com.anago.twitchmod.xposed.view.adapter

import android.text.Spanned
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anago.twitchmod.xposed.hooks.HookGlideHelper


class ChatAdapter(private val messages: List<Spanned>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = TextView(parent.context)
        textView.textSize = 14F
        textView.maxLines = 1
        textView.ellipsize = TextUtils.TruncateAt.END
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val textView = (holder.itemView as TextView)
        val message = messages[position]

        textView.text = message
        HookGlideHelper.loadImagesFromSpanned(
            holder.itemView.context,
            message,
            textView
        )
    }

    override fun getItemCount(): Int {
        return messages.size
    }
}