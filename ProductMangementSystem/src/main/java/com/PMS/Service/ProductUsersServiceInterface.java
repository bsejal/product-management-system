package com.PMS.Service;

import java.util.List;
import java.util.Optional;

import com.PMS.Model.ProductUsers;

public interface ProductUsersServiceInterface {
	
	//CRUD Operation
	
	//get all users
	public  List<ProductUsers> getProductUsers();
	
	//save or add new user
	public String addUser(ProductUsers newUser);
	
	//save or add new Admin
	public String addAdmin(ProductUsers newAdmin);
	
	//update user
	public String updateUser(ProductUsers newUser);
	public Optional<ProductUsers> getProductUsersById(int id);
	
	//delete user
	public String deleteUser(int id);
	
	//delete admin
	public String deleteAdmin(int id);
	
	
	

}
