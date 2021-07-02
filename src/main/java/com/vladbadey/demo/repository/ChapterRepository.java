package com.vladbadey.demo.repository;

import com.vladbadey.demo.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Chapter findChapterByCompositionName(String name);
}
