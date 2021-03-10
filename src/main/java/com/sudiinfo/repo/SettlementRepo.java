package com.sudiinfo.repo;

import com.sudiinfo.domain.databaseclasses.district.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementRepo extends JpaRepository<Settlement,Integer> {

    List<Settlement> findByName(String name);
}
