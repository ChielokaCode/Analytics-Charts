package com.chielokacodes.userorgapp.repository;


import com.chielokacodes.userorgapp.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {

}


