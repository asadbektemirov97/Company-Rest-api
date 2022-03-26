package com.example.task1.service;

import com.example.task1.dto.ApiResponse;
import com.example.task1.dto.WorkerDto;
import com.example.task1.entity.Worker;
import com.example.task1.repository.AddressRepository;
import com.example.task1.repository.DepartmentRepository;
import com.example.task1.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService {

    final WorkerRepository workerRepository;
    final AddressRepository addressRepository;
    final DepartmentRepository departmentRepository;


    public ApiResponse add(WorkerDto dto) {

        String name = dto.getName();
        String phoneNumber = dto.getPhoneNumber();

        Integer addressId = dto.getAddressId();
        Integer departmentId = dto.getDepartmentId();

        Worker worker = new Worker();
        worker.setName(name);
        worker.setPhoneNumber(phoneNumber);
        worker.setAddress(addressRepository.getById(addressId));
        worker.setDepartment(departmentRepository.getById(departmentId));

        workerRepository.save(worker);

        return new ApiResponse("Added", true, worker);
    }


    public ApiResponse list() {
        List<Worker> all = workerRepository.findAll();

        return new ApiResponse("List", true, all);
    }

    public ApiResponse one(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);

        if (optionalWorker.isPresent()) {
            Worker worker = optionalWorker.get();
            return new ApiResponse("Id", true, worker);
        }
        return new ApiResponse("Wrong", false);
    }

    public ApiResponse edit(Integer id, WorkerDto dto) {
        String name = dto.getName();
        String phoneNumber = dto.getPhoneNumber();

        Integer addressId = dto.getAddressId();
        Integer departmentId = dto.getDepartmentId();

        Optional<Worker> optionalWorker = workerRepository.findById(id);

        if (optionalWorker.isPresent()) {

            Worker worker = new Worker();
            worker.setName(name);
            worker.setPhoneNumber(phoneNumber);
            worker.setAddress(addressRepository.getById(addressId));
            worker.setDepartment(departmentRepository.getById(departmentId));

            workerRepository.save(worker);

            return new ApiResponse("Edited", true, worker);
        }
        return new ApiResponse("Wrong", false);
    }

    public ApiResponse delete(Integer id) {
        workerRepository.deleteById(id);

        return new ApiResponse("Deleted", true);
    }
}
