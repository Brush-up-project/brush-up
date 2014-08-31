package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
@SuppressWarnings("serial")
public class Order implements Serializable {
	private int orderId;
	private Date orderDate;
	private ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
	
	public Order()
	{
		this.orderDate = new Date();
	}

	public ArrayList<OrderLine> getOrderLines()
	{
		return orderLines;
	}
	public void addOrderLine(OrderLine o)
	{
		this.orderLines.add(o);
	}
	
	public void setOrderId(int id)
	{
		this.orderId = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderId()
	{
		return orderId;
	}
}
