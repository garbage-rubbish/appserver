package cn.com.cybertech.sdly.anno;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class App  extends Block{

    static CountDownLatch countDownLatch=new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        App app=new App();
        app.test();
        Arrays.<String>asList("","");

    }

    public static void test(){
        System.out.println("aaa");
    }




}

@FunctionalInterface
interface Inter{
    void test();

    default void test1(){
        test();
        System.out.println(2);
    }
}





class Block{

    public static void test(){
        System.out.println("ssss");
    }
    private static  BlockingQueue blockingQueue=new ArrayBlockingQueue(2);
    static ReentrantLock reentrantLock=new ReentrantLock();
    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {
       /* System.out.println(blockingQueue.add(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.remove(1));
        System.out.println(blockingQueue.remove(2));
        blockingQueue.take();
        blockingQueue.poll();*/
       /*condition.signal();
       reentrantLock.lock();
       condition.await();*/

    }
}

