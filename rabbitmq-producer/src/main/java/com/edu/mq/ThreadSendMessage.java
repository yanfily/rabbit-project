package com.edu.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author ysr
 * @version 1.0
 * @date 2020/1/10
 * @Description
 */
@ComponentScan
public class ThreadSendMessage {

    private static


    public static int count=0;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Send.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
        ExecutorService executor = Executors.newCachedThreadPool();
        Semaphore seamaphore=new Semaphore(100);
        for(int i=0;i<=100000;i++){
        executor.execute(()->{
            try{
                seamaphore.acquire();

                  //  rabbitTemplate.convertAndSend(Thread.currentThread().getName()+"100个用户并发,发送消息到消息队列----"+i);
                  add();


            }catch (Exception e){
                e.printStackTrace();
            }finally {
                seamaphore.release();
            }
        });
        }

        System.out.println(count);
    }

    private static void add() {
        count++;
    }


}
