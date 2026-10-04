package io.github.jessedeoliveira.playlistorg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.github.jessedeoliveira.playlistorg.model.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
}
