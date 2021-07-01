package com.example.demo.repo;

import com.example.demo.entity.Character;
import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<Character, Long> {
    boolean existsByName(String name);
}
