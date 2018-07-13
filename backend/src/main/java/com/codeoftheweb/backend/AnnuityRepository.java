package com.codeoftheweb.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AnnuityRepository extends JpaRepository<Annuity, Long> {
    Annuity findById(long id);
}
