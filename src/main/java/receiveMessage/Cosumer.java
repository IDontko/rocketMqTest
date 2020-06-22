package receiveMessage;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author gaoayang
 * create by gaoyang on 2020/6/17
 */
public class Cosumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer cosumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
        cosumer.setNamesrvAddr("localhost:9876");
        cosumer.subscribe("TopicTest", (String) null);
        cosumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        cosumer.start();
        System.out.println("cosumer started.%n");
    }
}
