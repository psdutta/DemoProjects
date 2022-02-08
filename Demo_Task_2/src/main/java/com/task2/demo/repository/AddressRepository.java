package com.task2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task2.demo.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
