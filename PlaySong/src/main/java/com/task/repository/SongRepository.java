package com.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.model.SongSchedule;

@Repository
public interface SongRepository extends JpaRepository<SongSchedule, Long> {
    Optional<SongSchedule> findTopByOrderByIdDesc();
}
