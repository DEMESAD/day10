package com.rest.springbootemployee.service;

import com.rest.springbootemployee.entity.Company;
import com.rest.springbootemployee.entity.Employee;
import com.rest.springbootemployee.exception.NoCompanyFoundException;
import com.rest.springbootemployee.repository.CompanyRepository;
import com.rest.springbootemployee.repository.JpaCompanyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private JpaCompanyRepository jpaCompanyRepository;


    public CompanyService(CompanyRepository companyRepository, JpaCompanyRepository jpaCompanyRepository) {
        this.companyRepository = companyRepository;
        this.jpaCompanyRepository = jpaCompanyRepository;
    }


    public List<Company> findAll() {
        return jpaCompanyRepository.findAll();
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        return this.jpaCompanyRepository.findAll(pageable).toList();
    }

    public Company findById(Integer companyId) {
        return jpaCompanyRepository.findById(companyId).orElseThrow(NoCompanyFoundException::new);
    }

    public Company create(Company company) {
        return companyRepository.create(company);
    }

    public void delete(Integer companyId) {
        companyRepository.delete(companyId);
    }

    public Company update(Integer companyId, Company toUpdateCompany) {
        Company existingCompany = companyRepository.findById(companyId);
        if (toUpdateCompany.getName() != null) {
            existingCompany.setName(toUpdateCompany.getName());
        }
        return existingCompany;
    }

    public List<Employee> getEmployees(Integer companyId) {
        Company company = this.findById(companyId);
        return company.getEmployees();
    }

}
