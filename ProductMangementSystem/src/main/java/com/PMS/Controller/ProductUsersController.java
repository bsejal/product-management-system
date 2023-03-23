package com.PMS.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.PMS.Model.ProductUsers;
import com.PMS.Service.ProductUserService;

@Controller
public class ProductUsersController {
	
	@Autowired
	ProductUserService service;
	
	@Autowired
	PasswordEncoder encoder;
	
	//CRUD Operation mapping
	
	@GetMapping("/index")
	public String index(Model model) {

		List<ProductUsers> users = service.getProductUsers();
		model.addAttribute("user", users);
		return "index";
	}
	@GetMapping("/register")
	public String register(Model model) {
		ProductUsers user =new ProductUsers();
		model.addAttribute("user",user);
		return "register";
	}
	@GetMapping("/registerAdmin")
	public String registerAdmin(Model model) {
		ProductUsers admin =new ProductUsers();
		model.addAttribute("admin",admin);
		return "registerAdmin";
	}
	
	@PostMapping("/saveuser")
	public String save(@ModelAttribute("user")ProductUsers newUsers) {
		newUsers.setPasscode(encoder.encode(newUsers.getPasscode()));	
		service.addUser(newUsers);
		return "redirect:/index";
	}
	@PostMapping("/saveadmin")
	public String saveAdmin(@ModelAttribute("admin")ProductUsers newAdmin) {
		newAdmin.setPasscode(encoder.encode(newAdmin.getPasscode()));	
		service.addAdmin(newAdmin);
		return "redirect:/index";
	}
//	@GetMapping("/delete/{id}")
//	public String deleteUsers(@PathVariable int id) {
//		service.deleteUser(id);
//		return "redirect:/index";
//	}
//	@GetMapping("/delete/{id}")
//	public String deleteAdmin(@PathVariable int id) {
//		service.deleteAdmin(id);
//		return "redirect:/index";
//	}
	
//	
	@GetMapping("/update/{id}")
	public String updateUsers(Model model,@PathVariable int id) {
	Optional<ProductUsers> user =service.getProductUsersById(id);
	model.addAttribute("user",user.get());
	return "updateUser";
	}
	
	@PostMapping("/updateUsers/{id}")
	public String UpdateProductUsers(@ModelAttribute("user") ProductUsers newUsers, @PathVariable int id) {
		newUsers.setId(id);
		newUsers.setPasscode(encoder.encode(newUsers.getPasscode()));
		service.updateUser(newUsers);
		return "redirect:/index";
	}
	
//	@GetMapping("/getUsers")
//	public ResponseEntity<List<ProductUsers>> getProductUsers(){
//		List<ProductUsers> productUsers=service.getProductUsers();
//		return ResponseEntity.status(HttpStatus.OK).body(productUsers);
//	}
//	
//	@PostMapping("/adduser")
//	public ResponseEntity<String> addUsers(@RequestBody ProductUsers newUsers){
//	newUsers.setPasscode(encoder.encode(newUsers.getPasscode()));	
//		String msg=service.addUser(newUsers);
//		return ResponseEntity.status(HttpStatus.OK).body(msg);
//	}
//	@PostMapping("/addadmin")
//	public ResponseEntity<String> addAdmin(@RequestBody ProductUsers newAdmin){
//		newAdmin.setPasscode(encoder.encode(newAdmin.getPasscode()));
//		String msg=service.addAdmin(newAdmin);
//		return ResponseEntity.status(HttpStatus.OK).body(msg);
//	}
//	@PutMapping("/updateuser")
//	public ResponseEntity<String> updateUsers(@RequestBody ProductUsers newUsers){
//		newUsers.setPasscode(encoder.encode(newUsers.getPasscode()));
//		String msg=service.updateUser(newUsers);
//		return ResponseEntity.status(HttpStatus.OK).body(msg);
//	}
//	@DeleteMapping("/deleteuser/{id}")
//	public ResponseEntity<String> deleteUsers(@PathVariable("id") int id){
//	String msg=service.deleteUser(id);
//	return ResponseEntity.status(HttpStatus.OK).body(msg);
//	}
//	@DeleteMapping("/deleteadmin/{id}")
//	public ResponseEntity<String> deleteAdmin(@PathVariable("id") int id){
//	String msg=service.deleteAdmin(id);
//	return ResponseEntity.status(HttpStatus.OK).body(msg);
//	}
//	
}
