package com.example.task1.service;

import com.example.task1.dto.ApiResponse;
import com.example.task1.dto.DepartmentDto;
import com.example.task1.entity.Department;
import com.example.task1.repository.CompanyRepository;
import com.example.task1.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    final DepartmentRepository departmentRepository;
    final CompanyRepository companyRepository;

    public ApiResponse add(DepartmentDto dto) {

        String name = dto.getName();
        Integer addressId = dto.getCompanyId();

        Department department = new Department();
        department.setName(dto.getName());
        department.setCompany(companyRepository.findById(dto.getCompanyId()).get());

        Department save = departmentRepository.save(department);

        return new ApiResponse("Added", true, save);
    }

    public ApiResponse list() {
        List<Department> all = departmentRepository.findAll();

        return new ApiResponse("List", true, all);
    }

    public ApiResponse one(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();

            return new ApiResponse("Id", true, department);
        }

        return new ApiResponse("Wrong", false);
    }

    public ApiResponse edit(Integer id, DepartmentDto dto) {
        String name = dto.getName();
        Integer addressId = dto.getCompanyId();

        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (optionalDepartment.isPresent()) {
            Department department = new Department();
            department.setName(dto.getName());
            department.setCompany(companyRepository.findById(dto.getCompanyId()).get());

            Department save = departmentRepository.save(department);

            return new ApiResponse("Edited", true, save);
        }
        return new ApiResponse("Wrong", false);
    }

    public ApiResponse delete(Integer id) {
        departmentRepository.deleteById(id);

        return new ApiResponse("Deleted", true);
    }
}
