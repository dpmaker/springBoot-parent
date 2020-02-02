package boot.activemq;

import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service("producer")
public class Producer {
	static int count = 0;
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private Queue queue;

	@Autowired
	private Topic topic;

	//@Scheduled(fixedDelay = 2000) // 每2s执行1次
	public void send(String name) {
		this.jmsMessagingTemplate.convertAndSend(this.queue, "hello, queue  " + name + "  " + count++);
	}

//	@Scheduled(fixedDelay = 2000) // 每2s执行1次
	public void  send2(String name) {
		this.jmsMessagingTemplate.convertAndSend(this.topic, "hello, topic " + name + "  " + count++);
	}

	@JmsListener(destination = "out.queue")
	public void consumerMessage(String text) {
		System.out.println("从out.queue队列收到的回复报文为:" + text);
	}

}
