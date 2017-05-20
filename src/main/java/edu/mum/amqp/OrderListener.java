package edu.mum.amqp;

import edu.mum.domain.Order;

public class OrderListener {

	public void listen(Order order) {
			
		String name = order.getItems().get(0).getProduct().getName();
		System.out.println("---------- TOPIC Order for Product: " + name);
	}
}
