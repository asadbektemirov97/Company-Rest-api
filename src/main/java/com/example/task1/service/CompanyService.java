package com.example.task1.service;

import com.example.task1.dto.ApiResponse;
import com.example.task1.dto.CompanyDto;
import com.example.task1.entity.Address;
import com.example.task1.entity.Company;
import com.example.task1.repository.AddressRepository;
import com.example.task1.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    final AddressRepository addressRepository;
    final CompanyRepository companyRepository;

    public ApiResponse add(CompanyDto dto) {

        String corpName = dto.getCorpName();
        String directorName = dto.getDirectorName();

        Integer homeNumber = dto.getHomeNumber();
        String street = dto.getStreet();
//===============================================

        Address address = new Address();

        address.setStreet(street);
        address.setHomeNumber(homeNumber);

        addressRepository.save(address);
        // address saqlandi

        //========================================
        Company company = new Company();

        company.setAddress(address);
        company.setCorpName(corpName);
        company.setDirectorName(directorName);

        Company save = companyRepository.save(company);

        return new ApiResponse("Added", true, save);
    }

    public ApiResponse list() {
        List<Company> all = companyRepository.findAll();

        return new ApiResponse("List", true, all);
    }

    public ApiResponse one(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();

            return new ApiResponse("Id", true, company);
        } else {
            return new ApiResponse("Wrong", false);
        }
    }

    public ApiResponse edit(Integer id, CompanyDto dto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);

        String corpName = dto.getCorpName();
        String directorName = dto.getDirectorName();

        Integer homeNumber = dto.getHomeNumber();
        String street = dto.getStreet();

        if (optionalCompany.isPresent()) {
            Address address = new Address();
            address.setStreet(street);
            address.setHomeNumber(homeNumber);

            addressRepository.save(address);
            // address saqlandi

            Company company = optionalCompany.get();
            company.setAddress(address);
            company.setCorpName(corpName);
            company.setDirectorName(directorName);

            // aks holda o'zgarmaydi
            Company save = companyRepository.save(company);

            return new ApiResponse("Edited", true, save);
        }
        return new ApiResponse("Wrong", false);
    }

    public ApiResponse delete(Integer id) {
        companyRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }
}
