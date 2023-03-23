package com.PMS.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.PMS.Exception.ProductException;
import com.PMS.Model.Product;
import com.PMS.Service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService service;
	
	
	//GET ALL PRODUCTS
//	@GetMapping("getproducts")
//	public ResponseEntity<List<Product>> getProducts(){
//		List<Product> products =service.getProducts();
//		return ResponseEntity.status(HttpStatus.OK).body(products);
//	}
	
	
	@GetMapping("/product")
	public String product(Model model) {
		List<Product> products = service.getProducts();
		model.addAttribute("product",products);
		return "index1";
	}
	@GetMapping("/product1")
	public String productUser(Model model) {
		List<Product> products = service.getProducts();
		model.addAttribute("products",products);
		return "product";
	}
	@GetMapping("/addproduct")
	public String addproduct(Model model) {
		Product products =new Product();
		model.addAttribute("product",products);
		return "addproduct";
	}
	@PostMapping("/saveproduct")
	public String saveproduct(@ModelAttribute("product")Product newProduct) {
		service.addProduct(newProduct);
		System.out.println("done");
		return "redirect:/product";
	}

	@GetMapping("/updateproduct/{id}")
	public String updateProduct(Model model, @PathVariable int id) {

		Optional<Product> products = service.getProductById(id);
		model.addAttribute("product", products.get());

		return "updateproduct";
	}
	@PostMapping("/updatedetails/{id}")
	public String updateProductDetails(@ModelAttribute("product") Product newProduct, @PathVariable int id) {
		newProduct.setId(id);
		service.updateProduct(newProduct);
		return "redirect:/product";
	}

	@GetMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable int id) {
		service.deleteProduct(id);
		return "redirect:/product";
	}

	//ADD PRODUCTS
//	@PostMapping("/addproduct")
//	public ResponseEntity<String> addProduct(@RequestBody @Valid Product newProduct){
//		String msg=service.addProduct(newProduct);
//		return ResponseEntity.status(HttpStatus.OK).body(msg);
//	}
	//UPDATE PRODUCT
//	@PutMapping("/updateproduct")
//	public ResponseEntity<String> updateProduct(@RequestBody Product newProduct){
//		String msg=service.updateProduct(newProduct);
//		return ResponseEntity.status(HttpStatus.OK).body(msg);
//	}
//	//DELETE PRODUCT
//	@DeleteMapping("/deleteproduct/{id}")
//	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
//		String msg=service.deleteProduct(id);
//		return ResponseEntity.status(HttpStatus.OK).body(msg);
//	}
	
	//EXCEPTION HANDLING
	@ExceptionHandler(ProductException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,String> ProductException(ProductException ex){
		Map<String,String> error=new HashMap<>();
		error.put("Error", ex.getMessage());
		return error;
		
	}
	
	
	
}
