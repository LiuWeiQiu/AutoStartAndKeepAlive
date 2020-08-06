# AutoStartAndKeepAlive
### 开机自启

在 Android 8.0 之前，实现开机自启可以通过静态注册监听系统启动广播实现。步骤如下：

1. 创建 广播接收器

   ~~~java
   public class BootReceiver extends BroadcastReceiver {
   
       @Override
       public void onReceive(Context context, Intent intent) {
           Log.i(TAG, "BootReceiver onReceive: ");
       }
   }
   ~~~

2. 将广播接收器注册到 manifest

   ~~~xml
   <receiver
       android:name=".BootReceiver">
       <intent-filter>
           <action android:name="android.intent.action.BOOT_COMPLETED" />
   
           <category android:name="android.intent.category.DEFAULT" />
       </intent-filter>
   </receiver>
   ~~~

3. 添加启动广播监听权限

   ~~~xml
   <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
   ~~~

而，自 Android 8.0 之后，静态注册的广播接收器将无法收到来自系统的隐式广播，[官网说明](https://developer.android.com/guide/components/broadcast-exceptions)，可以尝试在 Receiver 中添加 `android:directBootAware="true"` 属性以及 ` <action android:name="android.intent.action.LOCKED_BOOT_COMPLETED" />`  ，不保证有效（手动滑稽）

~~~xml
<receiver
    android:name=".BootReceiver"
    android:directBootAware="true"
    android:permission="android.permission.RECEIVE_BOOT_COMPLETED">
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED" />
        <action android:name="android.intent.action.LOCKED_BOOT_COMPLETED" />

        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</receiver>
~~~

注意：

- SD 卡加载是在开机广播发出后，所以 app 必须安装在内存中，在 application 根下添加该属性

  ```xml
  android:installLocation="internalOnly"
  ```

- APP 安装后必须启动一次，无界面的应用可以通过其他应用拉起
- 各大厂商的系统可能会禁止应用自启动，需要自己设置为允许，该设置一般在自启动管理或者电源管理中，实在找不到我也没办法~~~

- 如果设备有权限，可以将 app 作为系统应用 push 到 system/app 目录下，彻底解决接收不到启动广播的问题

### 进程保活

见代码，有空再补充！

### 参考 

自启动

https://www.trinea.cn/android/android-boot_completed-not-work/

保活

http://www.52im.net/thread-2881-1-1.html