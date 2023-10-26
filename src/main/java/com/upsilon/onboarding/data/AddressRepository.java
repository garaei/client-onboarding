package com.upsilon.onboarding.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressRepository
        extends
            JpaRepository<Address, Long>,
            JpaSpecificationExecutor<Address> {

}
