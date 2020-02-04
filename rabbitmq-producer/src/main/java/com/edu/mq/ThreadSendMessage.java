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
 * @Description   模拟100个用户并发向队列发送10000条数据
 */
@ComponentScan
public class ThreadSendMessage {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Send.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
        ExecutorService executor = Executors.newCachedThreadPool();
        Semaphore seamaphore=new Semaphore(100);
        for(int i=0;i<10000;i++){
            int index=i;
        executor.execute(()->{
            try{
                seamaphore.acquire();

                   rabbitTemplate.convertAndSend(Thread.currentThread().getName()+",100个用户并发,发送消息到消息队列----"+index);



            }catch (Exception e){
                e.printStackTrace();
            }finally {
                seamaphore.release();
            }
        });
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        context.close();


    }




}
