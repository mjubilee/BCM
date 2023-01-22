package com.bcm.truckingdeliverystatussapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcm.truckingdeliverystatussapi.model.UserCategory;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {

}