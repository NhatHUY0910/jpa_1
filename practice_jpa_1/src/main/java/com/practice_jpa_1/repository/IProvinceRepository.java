package com.practice_jpa_1.repository;

import com.practice_jpa_1.model.Province;
import org.springframework.data.repository.CrudRepository;

public interface IProvinceRepository extends CrudRepository<Province, Long> {
}
