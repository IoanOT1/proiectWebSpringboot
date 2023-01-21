package com.example.ex1.repository;

import com.example.ex1.model.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarrantyRepository extends JpaRepository<Warranty,Integer> {
}
