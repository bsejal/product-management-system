package com.PMS.Service;

import java.util.List;
import java.util.Optional;

import com.PMS.Model.Product;

public interface productServiceInterface {

	// CRUD operation
	
	//get products
	public List<Product> getProducts();
	public Optional<Product> getProductById(int id);
	
	//save new product
	public String addProduct(Product newProduct);
	
	//update product
	public String updateProduct(Product newProduct);
	
	//delete product
	public String deleteProduct(int id);
	public void deleteProductById(int id);
	
}
