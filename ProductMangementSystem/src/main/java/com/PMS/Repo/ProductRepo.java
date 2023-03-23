package com.PMS.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PMS.Model.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
