package com.flynn.tax_application.repository;

import com.flynn.tax_application.model.Settlement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementRepository extends CrudRepository<Settlement, Long> {
}
