package com.example.ex1.repository;

import com.example.ex1.model.Signature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureRepository extends JpaRepository<Signature,Integer> {
}
