package com.flynn.tax_application.repository;

import com.flynn.tax_application.model.Settlement;
import com.flynn.tax_application.model.TaxClient;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaxClientRepository extends CrudRepository<TaxClient, Long> {
}
