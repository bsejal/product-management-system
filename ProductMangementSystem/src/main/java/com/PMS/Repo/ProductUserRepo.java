package com.PMS.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PMS.Model.ProductUsers;
@Repository
public interface ProductUserRepo extends JpaRepository<ProductUsers, Integer> {

	 public Optional<ProductUsers> findByEmail(String email);

}
