package com.example.task1.controller;

import com.example.task1.dto.ApiResponse;
import com.example.task1.dto.CompanyDto;
import com.example.task1.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/add")
    public HttpEntity<?> addCompany(@RequestBody CompanyDto dto){
        ApiResponse apiResponse =  companyService.add(dto);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK: HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/list")
    public HttpEntity<?> all() {
        ApiResponse apiResponse =  companyService.list();

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK: HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> one(@PathVariable Integer id) {
        ApiResponse apiResponse = companyService.one(id);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody CompanyDto dto, @PathVariable Integer id) {
        ApiResponse apiResponse = companyService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    @DeleteMapping("delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = companyService.delete(id);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }
}
