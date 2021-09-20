package com.example.springmultithread.repo;

import com.example.springmultithread.models.Implementation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImplementationRepo extends JpaRepository<Implementation, Long> {

    Implementation getImplementationById(Long id);
}
