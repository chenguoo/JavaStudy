http://www.importnew.com/15731.html
常见面试题

可以为你的下次面试准备以下一些CountDownLatch相关的问题：

解释一下CountDownLatch概念?
   CountDownLatch是在java1.5被引入的，跟它一起被引入的并发工具类还有CyclicBarrier、Semaphore、ConcurrentHashMap和BlockingQueue，
   它们都存在于java.util.concurrent包下。CountDownLatch这个类能够使一个线程等待其他线程完成各自的工作后再执行。
   例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行。

   CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。
   当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。

CountDownLatch 和CyclicBarrier的不同之处?

给出一些CountDownLatch使用的例子?

CountDownLatch 类中主要的方法?