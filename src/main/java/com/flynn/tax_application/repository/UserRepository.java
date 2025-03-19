package com.flynn.tax_application.repository;

import com.flynn.tax_application.model.Settlement;
import com.flynn.tax_application.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
