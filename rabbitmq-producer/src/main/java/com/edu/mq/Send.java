package com.edu.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ysr
 * @version 1.0
 * @date 2020/1/10
 * @Description   发送消息关注exchange,routingKey
 *                默认DEFAULT_ROUTING_KEY="" DEFAULT_EXCHANGE=""
 *                RabbitTemplate 设置queqe默认值为 default.queue RoutingKey 默认 default.queue
  */

@ComponentScan
public class Send {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Send.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);


         // 默认Quque,rabbitTemplate配置了值,(默认DEFAULT_ROUTING_KEY="" DEFAULT_EXCHANGE="")
        //   rabbitTemplate.convertAndSend("发送消息默认Quque---default.quque成功");
        //   rabbitTemplate.convertAndSend("info.direct.info","debug","发送消息debugQueue");




        //后置处理器可更改消息参数  或重新new 一个新的消息对象
        rabbitTemplate.convertAndSend("info.direct.info","debug","发送消息到debugQueue", message->{
            System.out.println("未处理前的消息属性:"+message.getMessageProperties());
            System.out.println("未处理前的消息体:----"+new String(message.getBody()));
            MessageProperties properties=new MessageProperties();
            properties.setHeader("dec","经过处理后的头部2222");
            Message newMessage=new Message("处理过的消息,发送消息到debugQueue".getBytes(),properties);
            System.out.println("处理后的消息体:----"+new String(newMessage.getBody()));
            System.out.println("处理后的消息属性:"+properties);
            return newMessage;
        });

        context.close();
    }
}
