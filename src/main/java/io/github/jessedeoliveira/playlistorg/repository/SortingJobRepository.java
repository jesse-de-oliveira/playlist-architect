package io.github.jessedeoliveira.playlistorg.repository;

import io.github.jessedeoliveira.playlistorg.model.SortingJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SortingJobRepository extends JpaRepository<SortingJob, UUID> {
}
