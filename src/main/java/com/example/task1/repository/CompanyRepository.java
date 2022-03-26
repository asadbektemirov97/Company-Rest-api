package com.example.task1.repository;

import com.example.task1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    List<Company> getById = new ArrayList<>();
}