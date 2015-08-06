package operation.service.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MyHelloService {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
    public void capitalize(String foo) {
    	Message msg= rabbitTemplate.receive("hello");
       System.out.println(new String(msg.getBody()));
    }
}
