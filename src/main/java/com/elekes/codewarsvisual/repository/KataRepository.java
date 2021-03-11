package com.elekes.codewarsvisual.repository;

import com.elekes.codewarsvisual.entity.Kata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KataRepository extends JpaRepository<Kata, Long> {
}
