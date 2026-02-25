package com.example.demo.relation;

import com.example.demo.relation.model.A;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ARepository extends JpaRepository<A, Long> {

}
