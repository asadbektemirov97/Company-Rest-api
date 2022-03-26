package com.example.task1.controller;

import com.example.task1.dto.AddressDto;
import com.example.task1.dto.ApiResponse;
import com.example.task1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//     /api/address/...
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    // qo'shadi
    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody AddressDto dto) {
        ApiResponse apiResponse = addressService.add(dto);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    // hammasini listi
    @GetMapping("/list")
    public HttpEntity<?> all() {
        ApiResponse apiResponse = addressService.all();

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    // bittasi kerak :
    @GetMapping("/{id}")
    public HttpEntity<?> one(@PathVariable Integer id) {
        ApiResponse apiResponse = addressService.one(id);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody AddressDto dto, @PathVariable Integer id) {
        ApiResponse apiResponse = addressService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = addressService.delete(id);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }
}
