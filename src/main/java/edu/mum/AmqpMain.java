package edu.mum;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import edu.mum.amqp.AnswerServiceImpl;
import edu.mum.amqp.DirectServiceImpl;
import edu.mum.amqp.OrderService;
import edu.mum.amqp.OrderServiceImpl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class AmqpMain {

	public static void main(String[] args) {
		ApplicationContext context = new GenericXmlApplicationContext(
				"classpath:META-INF/spring/order-app-context.xml");

		RabbitTemplate topicTemplate = context.getBean("topicTemplate", RabbitTemplate.class);
		OrderService orderService = new OrderServiceImpl(); // Publish to Topic
		orderService.publish(topicTemplate);

		// Publish to "direct" exchange on order.key == directQueue
		RabbitTemplate directTemplate = context.getBean("directTemplate", RabbitTemplate.class);
		OrderService directService = new DirectServiceImpl();
		directService.publish(directTemplate);

		// Publish to "direct" exchange on order.answer == answerQueue
		RabbitTemplate answerTemplate = context.getBean("answerTemplate", RabbitTemplate.class);
		OrderService answerService = new AnswerServiceImpl();
		answerService.publish(answerTemplate);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// context.close();
	}
}
