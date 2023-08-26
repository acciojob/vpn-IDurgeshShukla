package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
        Admin admin = new Admin(username,password);
        return adminRepository1.save(admin);
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        Admin admin = adminRepository1.findById(adminId).get();
        List<ServiceProvider> serviceProviderList = serviceProviderRepository1.findByName(providerName);
        ServiceProvider serviceProvider = serviceProviderList.get(0);
        serviceProvider.setAdmin(admin);
        admin.getServiceProviderList().add(serviceProvider);
        return adminRepository1.save(admin);
    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{
        ServiceProvider serviceProvider = serviceProviderRepository1.findById(serviceProviderId).get();
        Country country = countryRepository1.findByCountryName(countryName);
        country.setServiceProvider(serviceProvider);
        serviceProvider.getCountryList().add(country);
        countryRepository1.save(country);
       return serviceProviderRepository1.save(serviceProvider);
    }
}
