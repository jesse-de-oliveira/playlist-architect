package io.github.jessedeoliveira.playlistorg.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tracks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String youtubeTrackId;

    @Column(nullable = false)
    private String title;

    private String artist;

    private String album;

    private String resolvedGenre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrackStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private SortingJob sortingJob;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TrackGenreSuggestion> suggestions = new ArrayList<>();

}
