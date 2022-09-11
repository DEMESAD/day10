package com.rest.springbootemployee.service;

import com.rest.springbootemployee.entity.Employee;
import com.rest.springbootemployee.exception.NoEmployeeFoundException;
import com.rest.springbootemployee.repository.JpaEmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private JpaEmployeeRepository jpaEmployeeRepository;

    public EmployeeService(JpaEmployeeRepository jpaEmployeeRepository) {
        this.jpaEmployeeRepository = jpaEmployeeRepository;
    }

    public List<Employee> findAll() {
        return jpaEmployeeRepository.findAll();
    }

    public Employee update(int id, Employee toUpdateEmployee) {
        //    requirement: update age and salary
        Employee existingEmployee = findById(id);
        if (toUpdateEmployee.getAge() != null) {
            existingEmployee.setAge(toUpdateEmployee.getAge());
        }
        if (toUpdateEmployee.getSalary() != null) {
            existingEmployee.setSalary(toUpdateEmployee.getSalary());
        }
        jpaEmployeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    public Employee findById(Integer id) {
        return jpaEmployeeRepository.findById(id)
                .orElseThrow(NoEmployeeFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return jpaEmployeeRepository.findByGender(gender);
    }

    public List<Employee> findByPage(int page, int pageSize) {
        Pageable pageRequest = PageRequest.of(page-1, pageSize);
        return jpaEmployeeRepository.findAll(pageRequest).toList();
    }

    public void delete(Integer id) {
        try {
            jpaEmployeeRepository.deleteById(id);
        } catch (Exception exception) {
            throw new NoEmployeeFoundException();
        }
    }

    public Employee create(Employee employee) {
        return jpaEmployeeRepository.save(employee);
    }
}
