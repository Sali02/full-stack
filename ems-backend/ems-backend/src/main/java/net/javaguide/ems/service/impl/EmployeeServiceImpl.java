package net.javaguide.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguide.ems.dto.EmployeeDto;
import net.javaguide.ems.entity.Department;
import net.javaguide.ems.entity.Employee;
import net.javaguide.ems.expection.ResourceNotFoundException;
import net.javaguide.ems.mapper.EmployeeMapper;
import net.javaguide.ems.repository.DepartmentRepository;
import net.javaguide.ems.repository.EmployeeRepository;
import net.javaguide.ems.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        System.out.println("until here it workssss");

        System.out.println("The employeedto get department id is : " + employeeDto.getFirstName());

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        System.out.println("here we are after we mapped the employeeDto to a normal Employee");

        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("department with that id doesn't exists " + employeeDto.getDepartmentId()));


        System.out.println("here we are after we made all the blabla");

        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeID) {
        Employee employee =  employeeRepository.findById(employeeID)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee doesn't exist with given id: " + employeeID));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getALlEmployees() {

        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee with given ID wasn't finded: " + employeeId)
        );

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("department with that id doesn't exists " + updatedEmployee.getDepartmentId()));


        employee.setDepartment(department);

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto((updatedEmployeeObj));
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee with given ID wasn't finded: " + employeeId)
        );

        employeeRepository.deleteById(employeeId);
    }


}
