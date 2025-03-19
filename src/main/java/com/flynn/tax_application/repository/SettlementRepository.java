package com.flynn.tax_application.repository;

import com.flynn.tax_application.model.Settlement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementRepository extends CrudRepository<Settlement, Long> {
    @Query("SELECT s FROM Settlement s WHERE s.id IN :ids")
    List<Settlement> findAllByIds(@Param("ids") List<Long> ids);
}
