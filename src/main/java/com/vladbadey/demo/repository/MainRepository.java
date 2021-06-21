package com.vladbadey.demo.repository;

import com.vladbadey.demo.entity.Composition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainRepository extends JpaRepository<Composition, Long> {

}
