package com.elekes.codewarsvisual.repository;

import com.elekes.codewarsvisual.entity.Kata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface KataRepository extends JpaRepository<Kata, Long> {

    List<Kata> findByCodewarsIdIn(Set<String> idList);
}
