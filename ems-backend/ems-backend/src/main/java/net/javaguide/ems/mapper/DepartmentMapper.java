package net.javaguide.ems.mapper;

import net.javaguide.ems.dto.DepartmentDto;
import net.javaguide.ems.entity.Department;

public class DepartmentMapper {

    //convert department jpa entity into a department dto

    public static DepartmentDto mapToDepartmentDto(Department department){
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription()
        );
    }

    //convert department dto into department jpa entity
    public static Department mapToDepartment(DepartmentDto departmentDto){
        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription()
        );
    }
}