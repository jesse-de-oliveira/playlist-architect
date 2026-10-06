package io.github.jessedeoliveira.playlistorg.repository;

import io.github.jessedeoliveira.playlistorg.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {


}
