直达某activity

    new-instance v0, Landroid/content/Intent;

    :try_start_2
    const-string v1, "类名"

    invoke-static {v1}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;
    :try_end_7
    .catch Ljava/lang/ClassNotFoundException; {:try_start_2 .. :try_end_7} :catch_f

    move-result-object v1

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v0}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V

    return-void

    :catch_f
    move-exception v0

    new-instance v1, Ljava/lang/NoClassDefFoundError;

    invoke-virtual {v0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v1, v0}, Ljava/lang/NoClassDefFoundError;-><init>(Ljava/lang/String;)V





.method protected onStart()V
    .registers 1 （寄存器数量记得改）
    .annotation runtime Ljava/lang/Override;
    .end annotation

    invoke-super {p0}, L当前类名;->onStart()V

    
    return-void
.end method
