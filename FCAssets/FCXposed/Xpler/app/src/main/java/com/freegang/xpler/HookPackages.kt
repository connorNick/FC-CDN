package com.freegang.xpler


/**
 * 你只需要在下面增加需要被hook的app包名,
 * 然后在 `HookMain` 中写你的Hook逻辑
 */
object HookPackages {
    val packages = listOf(
        "com.freegang.xpler", //mine package name, must be placed first
        "com.tencent.mm",
    )
}