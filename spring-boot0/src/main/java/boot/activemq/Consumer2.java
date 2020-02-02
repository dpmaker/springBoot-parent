package boot.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class Consumer2 {
	 @JmsListener(destination = "promoteAct")
	    public void receiveQueue(String consumer) {
	        System.out.println(consumer+" queue 消息已经消费了，消费者2");
	    }
	 
 @JmsListener(destination = "topicAct")
	 @SendTo("out.queue")  
	    public String receiveTopic(String consumer) {
	        System.out.println(consumer+"___   topic 消息已经消费了，消费者2");
	        return "123";
	    }
	 
	 
}
