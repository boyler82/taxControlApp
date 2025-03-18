package com.flynn.tax_application.repository;

import com.flynn.tax_application.model.TaxClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxClientRepository extends CrudRepository<TaxClient, Long> {
}
