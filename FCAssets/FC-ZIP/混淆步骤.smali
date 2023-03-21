'首先使用ProGuard混淆
'混淆完以后使用ArmPro控制流全部类➕字符串加密v2➕资源ID加密
'再把对应的类名还进去 isModuleActive 还需要修改ArmPro字符串加密特征
'搜索🔎 $(III)Ljava/lang/String; 全部替换为 StopKill(III)Ljava/lang/String;
'搜索🔎 $:[S 全部替换为 StopKill:[S
'做完前面的步骤再来使用NP控制流 
'NP控制流配置 字符串加密✔️ 加密资源ID✔️ DEX优化✔️ 花指令✔️ 指令替换✔️ 数字混淆✔️ 扁平化✔️  防一键解密✔
️'加密完了以后 再来ArmPro套娃混淆一遍 
'ArmPro控制流全部类➕字符串加密v2➕资源ID加密➕函数调用分离（需要注意的是除了MainActivity不分离以外其他类全选即可！）
