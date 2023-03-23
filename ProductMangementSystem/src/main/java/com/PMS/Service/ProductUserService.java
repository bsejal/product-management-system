package com.PMS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PMS.Exception.ProductUserException;
import com.PMS.Model.ProductUsers;
import com.PMS.Repo.ProductUserRepo;

@Service
public class ProductUserService implements ProductUsersServiceInterface, UserDetailsService {
	@Autowired
	ProductUserRepo repo;

	@Override
	public List<ProductUsers> getProductUsers() {
		List<ProductUsers> productUsers = repo.findAll();
		System.out.println(productUsers.toString());
		return productUsers;
	}

	@Override
	public String addUser(ProductUsers newUser) {
		Optional<ProductUsers> user = repo.findByEmail(newUser.getEmail());
		if (user.isPresent()) {
			throw new ProductUserException("user alredy exist");
		} else {
			newUser.setRole("ROLE_USER");
			repo.save(newUser);
			return "user added successfully";
		}
	}

	@Override
	public String updateUser(ProductUsers newUser) {
		Optional<ProductUsers> productUsers = repo.findById(newUser.getId());
		if (productUsers.isPresent()) {
			newUser.setRole("ROLE_ADMIN");
			repo.save(newUser);
			throw new ProductUserException("user update successfully");
		}
		return "select proper id";
	}

	@Override
	public String deleteUser(int id) {
		Optional<ProductUsers> user = repo.findById(id);
		if (user.isPresent() && user.get().getRole().equals("ROLE_USER")) {
			// if (user.isPresent()){
			repo.deleteById(id);
			throw new ProductUserException("user deleted successfully");
		}
		return "select proper id";
	}

	@Override
	public String addAdmin(ProductUsers newAdmin) {
		Optional<ProductUsers> admin = repo.findByEmail(newAdmin.getEmail());
		if (admin.isPresent()) {
			throw new ProductUserException("admin alredy exist");
		} else {
			newAdmin.setRole("ROLE_ADMIN");
			repo.save(newAdmin);
			return "admin added successfully";
		}
	}

	@Override
	public String deleteAdmin(int id) {
		Optional<ProductUsers> admin = repo.findById(id);
		if (admin.isPresent() && admin.get().getRole().equals("ROLE_ADMIN")) {
			// if (admin.isPresent()){
			repo.deleteById(id);
			throw new ProductUserException("admin deleted successfully");
		}
		return "select proper id";
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ProductUsers user = repo.findByEmail(username).get();
		user.setRole(user.getRole().toUpperCase());
		return user;

	}

	@Override
	public Optional<ProductUsers> getProductUsersById(int id) {
		return repo.findById(id);
	}
}
