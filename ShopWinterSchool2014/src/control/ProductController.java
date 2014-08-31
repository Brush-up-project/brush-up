package control;

import java.util.ArrayList;

import model.Product;
import persistence.ProductSerialize;

public class ProductController {
	private static ProductSerialize dataAccess = new ProductSerialize();
	private static ArrayList<Product> products = new ArrayList<Product>();
	
	
	public static void save(Product p)
	{
		if (p.getProductId() == 0)
		{//then it is new
			int highestId = findHighestPersonId();
			p.setProductId(highestId +1);
			products.add(p);
		}
		
		dataAccess.save(products); //write to disk.
	}
	
	
	
	private static int findHighestPersonId()
	{
		int highest = 0;
		for (Product p : products)
		{
			if (highest < p.getProductId())
				highest = p.getProductId();
		}
		return highest;
	}
	
	
	
	public static Product findProductById(int productId) {
		for (Product p : products)
		{
			if (p.getProductId() == productId)
			{
				return p;
			}
		}
		
		return null; //not found
	}
	
	public static ArrayList<Product> getAllProducts()
	{
		products = dataAccess.load();
		return products;
	}
	
	
	public static void deleteProductById(int productId)
	{
		for (int i=0; i < products.size(); i++)
		{
			if (products.get(i).getProductId() == productId)
			{
				products.remove(i);
				break;
			}
		}
		
		dataAccess.save(products);
	}
	
	public static ArrayList<Product> findProductsByName(String searchName) {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		
		for (Product p : products)
		{
			if (p.getName().toLowerCase().contains(searchName.toLowerCase())){
				foundProducts.add(p);
			}
		}
		
		return foundProducts;
	}
	public static ArrayList<Product> findProductsByPrice(double maxPrice) {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		
		for (Product p : products)
		{
			if (p.getPrice() <= maxPrice) {
				foundProducts.add(p);
			}
		}
		
		return foundProducts;
	}
}
