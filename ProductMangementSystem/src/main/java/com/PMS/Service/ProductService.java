package com.PMS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PMS.Exception.ProductException;
import com.PMS.Model.Product;
import com.PMS.Repo.ProductRepo;
@Service
public class ProductService implements productServiceInterface {

	@Autowired
	ProductRepo repo;

	@Override
	public List<Product> getProducts() {
		List<Product> products = repo.findAll();
		return products;
	}

	@Override
	public String addProduct(Product newProduct) {
		Optional<Product> product = repo.findById(newProduct.getId());
		if (product.isPresent()) {
			throw new ProductException("This Product Already Exist");
		}
		repo.save(newProduct);
		return "Product Added Successfully.";
	}

	@Override
	public String updateProduct(Product newProduct) {
		Optional<Product> product = repo.findById(newProduct.getId());
		if (product.isEmpty()) {
			throw new ProductException("This Product Doesn't Exist");
		}
		repo.save(newProduct);
		return "Product Updated Successfully.";
	}

	@Override
	public String deleteProduct(int id) {
		Optional<Product> product = repo.findById(id);
		if (product.isEmpty()) {
			throw new ProductException("This Product Doesn't Exist");
		}
		repo.delete(product.get());
		return "Product Deleted Successfully.";
	}

	public Optional<Product> getProductById(int id) {
		return repo.findById(id);
	}
	@Override
	public void deleteProductById(int id) {
		repo.deleteById(null);
	}

	
}
