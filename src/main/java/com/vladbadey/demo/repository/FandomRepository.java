package com.vladbadey.demo.repository;

import com.vladbadey.demo.entity.Fandom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FandomRepository extends JpaRepository<Fandom, Long> {

}
