package control;

import java.util.ArrayList;

import model.Order;
import model.OrderLine;
import model.Product;
import persistence.OrderSerialize;

public class OrderController {
	private static OrderSerialize dataAccess = new OrderSerialize();
	private static ArrayList<Order> orders = new ArrayList<Order>();
	
	public static void save(Order o)
	{
		if (o.getOrderId() == 0)
		{//then it is new
			int highestId = findHighestId();
			o.setOrderId(highestId +1);
			orders.add(o);
		}
		
		dataAccess.save(orders); //write to disk.
	}
	
	
	
	private static int findHighestId()
	{
		int highest = 0;
		for (Order o : orders)
		{
			if (highest < o.getOrderId())
				highest = o.getOrderId();
		}
		return highest;
	}
	
	
	
	public static Order findOrderById(int orderId) {
		for (Order o : orders)
		{
			if (o.getOrderId() == orderId)
			{
				return o;
			}
		}
		
		return null; //not found
	}
	
	public static ArrayList<Order> getAllOrders()
	{
		orders = dataAccess.load();
		return orders;
	}
	
	
	public static void deleteOrderById(int orderId)
	{
		for (int i=0; i < orders.size(); i++)
		{
			if (orders.get(i).getOrderId() == orderId)
			{
				orders.remove(i);
				break;
			}
		}
		
		dataAccess.save(orders);
	}
	
	
	public static ArrayList<OrderLine> getOrderLines(int orderId) {
		Order order = findOrderById(orderId);
		
		return order.getOrderLines();
	}
	
	public static void addOrderLine(Order order, Product p, int quantity)
	{
		OrderLine o = new OrderLine(p, quantity);
		
		order.addOrderLine(o);
	}
}
