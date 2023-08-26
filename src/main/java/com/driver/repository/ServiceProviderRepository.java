package com.driver.repository;

import com.driver.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Integer> {
    List<ServiceProvider> findByName(String name);
    List<ServiceProvider> findByUserUserId(Integer userId);
}
