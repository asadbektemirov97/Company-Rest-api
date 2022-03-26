package com.example.task1.service;

import com.example.task1.dto.AddressDto;
import com.example.task1.dto.ApiResponse;
import com.example.task1.entity.Address;
import com.example.task1.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // bean qiladi
@RequiredArgsConstructor
public class AddressService {

    final AddressRepository addressRepository;

    public ApiResponse add(AddressDto dto) {
        Address address = new Address();

        address.setHomeNumber(dto.getHomeNumber());
        address.setStreet(dto.getStreet());

        Address save = addressRepository.save(address);

        return new ApiResponse("Added", true, save);
    }

    public ApiResponse all() {
        List<Address> all = addressRepository.findAll();
        return new ApiResponse("List", true, all);
    }

    public ApiResponse one(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();

            return new ApiResponse("Id", true, address);
        } else {
            return new ApiResponse("Wrong", false);
        }
    }

    public ApiResponse edit(Integer id, AddressDto dto) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();

            address.setHomeNumber(dto.getHomeNumber());
            address.setStreet(dto.getStreet());

            // aks holda o'zgarmaydi
            Address save = addressRepository.save(address);

            return new ApiResponse("Edited", true, save);
        }
        return new ApiResponse("Wrong", false);
    }

    public ApiResponse delete(Integer id) {
        addressRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }
}
