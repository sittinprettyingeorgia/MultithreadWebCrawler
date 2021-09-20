package com.example.springmultithread.repo;

import com.example.springmultithread.models.designElements.DesignElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignElementRepo extends JpaRepository<DesignElement, Long> {

    DesignElement getDesignElementById(Long id);

}
