package com.vladbadey.demo.repository;

import com.vladbadey.demo.entity.Composition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositionRepository extends JpaRepository<Composition, Long> {

    Composition findByName(String name);

    void deleteByName(String name);
}
