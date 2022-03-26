package com.example.task1.controller;

import com.example.task1.dto.ApiResponse;
import com.example.task1.dto.DepartmentDto;
import com.example.task1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/add")
    public HttpEntity<?> addCompany(@RequestBody DepartmentDto dto){
        ApiResponse apiResponse =  departmentService.add(dto);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK: HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/list")
    public HttpEntity<?> all() {
        ApiResponse apiResponse =  departmentService.list();

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK: HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> one(@PathVariable Integer id) {
        ApiResponse apiResponse = departmentService.one(id);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody DepartmentDto dto, @PathVariable Integer id) {
        ApiResponse apiResponse = departmentService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    @DeleteMapping("delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = departmentService.delete(id);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }
}
