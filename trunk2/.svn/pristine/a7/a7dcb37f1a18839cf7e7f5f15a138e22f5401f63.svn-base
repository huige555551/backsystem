package operation;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@ComponentScan
@Configuration
@Component
public class RabbitmqConfiger {
	
	@Value("${rabbitmq.url}")
	private String rabbitmqUrl;
	@Value("${rabbitmq.port}")
	private int rabbitmqPort;
	@Value("${rabbitmq.userName}")
	private String userName;
	@Value("${rabbitmq.passWord}")
	private String passWord;
	
	@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =new CachingConnectionFactory(rabbitmqUrl,rabbitmqPort);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(passWord);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }
    
    @Bean
    public AmqpTemplate amqpTemplate(){
    		RabbitTemplate template = new RabbitTemplate(connectionFactory());
    		RetryTemplate retryTemplate = new RetryTemplate();
    		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
    		backOffPolicy.setInitialInterval(500);
    		backOffPolicy.setMultiplier(10.0);
    		backOffPolicy.setMaxInterval(10000);
    		retryTemplate.setBackOffPolicy(backOffPolicy);
    		template.setRetryTemplate(retryTemplate);
    		return template;
    }
    
   @Bean
   public RabbitTemplate rabbitTemplate() {
  	  RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory());
//  	  rabbitTemplate.setQueue("regexInfo");
      return rabbitTemplate;
   }
    
 
}
