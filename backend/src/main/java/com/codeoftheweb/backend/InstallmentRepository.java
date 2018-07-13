package com.codeoftheweb.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InstallmentRepository extends JpaRepository<Installment, Long> {
    Installment findById(long id);
}
