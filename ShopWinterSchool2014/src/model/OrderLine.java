package model;

import java.io.Serializable;
@SuppressWarnings("serial")
public class OrderLine implements Serializable {

		private Product p;
		private int quantity;
	
		public OrderLine(Product p, int quantity)
		{
			this.p = p;
			this.quantity = quantity;
		}

		public Product getProduct() {
			return p;
		}

		public void setProduct(Product p) {
			this.p = p;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		
}
