package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {

}
