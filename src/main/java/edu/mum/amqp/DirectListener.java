package edu.mum.amqp;

import edu.mum.domain.Order;

public class DirectListener {

	// Listens on the "directQueue" associated with the "direct" exchange
	public void listen(Order order) {
			
		String name = order.getItems().get(0).getProduct().getName();
		System.out.println("---------- DIRECT Order for Product: " + name);
	}
}
