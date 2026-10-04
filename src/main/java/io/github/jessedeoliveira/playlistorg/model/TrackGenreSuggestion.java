package io.github.jessedeoliveira.playlistorg.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "track_genre_suggestions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackGenreSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String genreName;

    private Double confidenceScore; // e.g. 0.85 (85% match from external API)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

}
