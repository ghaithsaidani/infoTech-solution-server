package org.example.repositories;

import org.example.models.AVA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AVARepository extends JpaRepository<AVA,Long> {
}
