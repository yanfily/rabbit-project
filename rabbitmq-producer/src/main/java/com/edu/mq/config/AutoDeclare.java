package com.edu.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author ysr
 * @version 1.0
 * @date 2020/1/10
 * @Description    rabbitMq自动申明Exchange,Queue,Binding配置类
 */
@Configuration
public class AutoDeclare {

    @Bean
    public Exchange directExchange(){
        return new DirectExchange("info.direct.info",true,false);
    }

    @Bean
    public Exchange topicExchange(){
        return new TopicExchange("info.topic.error",true,false);
    }

    @Bean
    public Exchange fanoutExchange(){
        return new FanoutExchange("info.fanout.error",true,false);
    }

    @Bean
    public Queue errorQueue(){
        return new Queue("error.queue",true,false,false);
    }

    @Bean
    public Queue infoQueue(){
        return new Queue("info.queue",true,false,false);
    }

    @Bean
    public Queue debugQueue(){
        return new Queue("debug.queue",true,false,false);
    }

    @Bean
    public Queue defaultQueue(){
        return new Queue("default.queue",true,false,false);
    }



    @Bean
    public Binding binding1(){
        return new Binding("info.queue", Binding.DestinationType.QUEUE,"info.direct.info","info",new HashMap());
    }

    @Bean
    public Binding binding2(){
        return new Binding("error.queue",Binding.DestinationType.QUEUE,"info.direct.info","error",new HashMap());
    }

    @Bean
    public Binding binding3(){
        return new Binding("debug.queue",Binding.DestinationType.QUEUE,"info.direct.info","debug",new HashMap());
    }
}
