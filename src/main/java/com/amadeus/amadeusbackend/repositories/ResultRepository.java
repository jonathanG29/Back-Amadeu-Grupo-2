package com.amadeus.amadeusbackend.repositories;

import com.amadeus.amadeusbackend.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}
