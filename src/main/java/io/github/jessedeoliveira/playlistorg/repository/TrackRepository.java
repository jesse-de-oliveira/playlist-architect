package io.github.jessedeoliveira.playlistorg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.github.jessedeoliveira.playlistorg.model.Track;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> findBySortingJobId(UUID sortingJobId);
}
