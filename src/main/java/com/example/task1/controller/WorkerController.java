package com.example.task1.controller;

import com.example.task1.dto.ApiResponse;
import com.example.task1.dto.WorkerDto;
import com.example.task1.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody WorkerDto dto) {
        ApiResponse apiResponse = workerService.add(dto);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/list")
    public HttpEntity<?> all() {
        ApiResponse apiResponse = workerService.list();

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> one(@PathVariable Integer id) {
        ApiResponse apiResponse = workerService.one(id);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody WorkerDto dto, @PathVariable Integer id) {
        ApiResponse apiResponse = workerService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    @DeleteMapping("delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = workerService.delete(id);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }
}
