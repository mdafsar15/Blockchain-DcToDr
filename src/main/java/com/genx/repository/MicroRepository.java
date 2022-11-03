package com.genx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genx.model.Birth;

@Repository
public interface MicroRepository extends JpaRepository<Birth, Long> {

	public Birth findByBirthId(Long birthId);

}
