package com.coeproject.dkglassdesigns.repository;

import com.coeproject.dkglassdesigns.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
}
