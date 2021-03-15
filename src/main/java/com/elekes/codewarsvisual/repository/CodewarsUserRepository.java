package com.elekes.codewarsvisual.repository;

import com.elekes.codewarsvisual.entity.CodewarsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodewarsUserRepository extends JpaRepository<CodewarsUser, Long> {

    Optional<CodewarsUser> findByUsername(String username);
}
