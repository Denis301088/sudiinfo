package com.sudiinfo.repo;

import com.sudiinfo.domain.databaseclasses.city.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreetRepo extends JpaRepository<Street,Integer> {

   List<Street> findByName(String name);
}
