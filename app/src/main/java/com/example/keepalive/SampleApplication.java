package com.example.keepalive;

/**
 * 开机自启动及保活的代码在 keep-alive module下，app 可直接依赖 module，或者使用 aar 包
 * 方式一 继承 KeepAliveApplication
 * 方式二 在 Application 中调用 Keeper.start(this);
 */
public class SampleApplication extends KeepAliveApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}

