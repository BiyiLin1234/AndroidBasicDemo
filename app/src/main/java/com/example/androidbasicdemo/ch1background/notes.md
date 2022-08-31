### Background

#### 1. Android Architecture
![Android-arch-img]
![Android-arch-img-wiki]
- Linux Kernel（提供安卓设备硬件的底层驱动）
  - Display Drivers, Camera Driver, Bluetooth Driver, Flash Memory Driver, Binder(IPC) Driver, USB Driver.
  - Keypad Driver, WiFi Driver, Audio Drivers, Power Management.
- HAL(Hardware abstraction layer)（为硬件供应商定义了一个标准接口）
  - 硬件抽象层，为硬件供应商定义了一个标准接口来实现。这样Android可以忽视实现细节，与具体实现解耦。
  - Define a standard interface for hardware vendors to implement, which enables Android to be agnostic about lower-level driver implementations.
- Libraries:（通过C/C++库提供数据库，3D绘图，浏览器内核等支持）+ （提供运行时库，允许开发者用Java编写Android）
  - Surface Manager, Media Framework, SQLite, OpenGL|ES, WebKit...
  - Android Runtime: Core Libraries, Dalvik Virtual Machine. 
    - 5.0之前为Dalvik，之后为ART，使得每个Android运行在独立的进程中。并拥有一个独立的虚拟机实例。
    - Dalvik和ART相对于Java虚拟机，专为移动设备定制，针对内存，CPU有限的情况进行了优化。
- Application Framework（应用框架层）
    - Activity Manager, Window Manager, Resource Manager, Location Manager.
    - 提供构建程序时可能用到的API
- Application
  - Home, Contacts, Phone, Browser.
  - 所有安装在手机中的应用都属于这一层。

#### Android系统提供
- 四大组件：Activity，Service，BroadcastReceiver和ContentProvider.
- 丰富的系统空间
- SQLite数据库：标准的API，支持SQL语法
- 多媒体：音乐，视频，拍照，录音。

#### 基本用法
- 对于String资源，在xml中通过@string/xxx引用，在代码中通过模块resources.getString(R.string.xxx)获取。
- AndroidStudio通过Gradle构建项目，基于Groovy语言编写项目配置。

<!-- images source define -->
[Android-arch-img]: https://github.com/BiyiLin1234/AndroidBasicDemo/blob/master/imgs/WeChatWorkScreenshot_0f53019c-8596-471b-916f-7f7f22ae51b7.png?raw=true
[Android-arch-img-wiki]: https://upload.wikimedia.org/wikipedia/commons/a/af/Android-System-Architecture.svg