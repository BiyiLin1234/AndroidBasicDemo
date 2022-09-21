## 广播
> 在网络中，一个IP网络范围内，最大的IP地址是被保留作为广播。
> IP范围192.168.0.XXX，子网掩码255.255.255.0，那么广播地址是192.168。0.XXX。
> 广播数据会被发到同一网络的所有端口，该网络的每台主机都会收到这条广播。

> Android也有一套广播机制。

#### 类型
- 标准广播（异步无先后顺序，不可截断）
- 有序广播（有先后顺序，可截断）

- 动态注册的广播（动态地调用ContextWrapper#registerReceiver和unregesterReceiver）缺点是程序启动后才能监听
- 静态注册的广播
  - 参考https://developer.android.com/guide/components/broadcast-exceptions
  - 为减少滥用一直在缩减