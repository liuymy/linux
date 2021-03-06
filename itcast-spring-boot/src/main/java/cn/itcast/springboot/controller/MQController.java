package cn.itcast.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/mq")
@RestController
public class MQController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @GetMapping("/sendMsg")
    public String sendMapMsg(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", 123);
        map.put("name", "itcast黑马");

        //参数1：要发送的模式名称（默认队列模式），参数2：发送的数据
        jmsMessagingTemplate.convertAndSend("spring.boot.map.queue", map);

        return "发送到spring.boot.map.queue消息成功。";
    }

    @GetMapping("/sendSmsMsg")
    public String sendSmsMsg(){
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", "13078274348");
        map.put("signName", "黑马");
        map.put("templateCode", "SMS_125018593");
        map.put("templateParam", "{\"code\":\"345345\"}");

        //参数1：要发送的模式名称（默认队列模式），参数2：发送的数据
        jmsMessagingTemplate.convertAndSend("itcast_sms_queue", map);

        return "发送到itcast_sms_queue消息成功。";
    }
}
