package com.api.rest.Repo;

import com.api.rest.Model.Fact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactRepo extends JpaRepository<Fact, Long> {
}
