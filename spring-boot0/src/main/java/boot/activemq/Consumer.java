package boot.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;


@Service
public class Consumer {
  // @JmsListener(destination = "promoteAct")
    public void receiveQueue(String consumer) {
        System.out.println(consumer+"消息已经消费了，消费者1");
    }
    
  //  @JmsListener(destination = "topicAct")
    public void receiveTopic(String consumer) {
        System.out.println(consumer+"__topic 消息已经消费了，消费者1");
    }
}
