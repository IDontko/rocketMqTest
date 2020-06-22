package sendMessage;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @author gaoayang
 * create by gaoyang on 2020/6/17
 * 发送延时消息
 */
public class ScheduleMessageProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("scheduleProducer");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        int totalMessageSend = 100;
        for (int i = 0; i < totalMessageSend; i++) {
            Message message = new Message("TestTopic", ("Hello schedule message" + i).getBytes());
            System.out.println("Hello schedule message" + i);
            message.setDelayTimeLevel(3);
            producer.send(message);
        }
        producer.shutdown();

    }
}
