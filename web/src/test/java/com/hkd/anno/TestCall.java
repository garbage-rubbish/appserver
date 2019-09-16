package com.hkd.anno;

import com.google.common.collect.Lists;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.function.IntConsumer;

public class TestCall {

    static ExecutorService executorService=new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10),new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws JSONException, IOException {
        ArrayList<Object> objects = Lists.newArrayList();
        objects.forEach(object->{

        });

    }

}


class ZeroEvenOdd {
    private int n;

    Semaphore semaphore=new Semaphore(1);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        printNumber.accept(0);

    }

    public void even(IntConsumer printNumber) throws InterruptedException {

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {

    }
}