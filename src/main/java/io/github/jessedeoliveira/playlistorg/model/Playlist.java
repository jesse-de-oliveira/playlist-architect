package io.github.jessedeoliveira.playlistorg.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "playlists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String targetGenre;

    @Column(nullable = false)
    private String youtubePlaylistId; // The ID returned by Python after creation

    private String youtubePlaylistUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private SortingJob sortingJob;
}
