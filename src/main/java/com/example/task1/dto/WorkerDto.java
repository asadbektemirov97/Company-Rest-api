package com.example.task1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class WorkerDto {

    private String name;
    private String phoneNumber;
    private Integer addressId;
    private Integer departmentId;
}
