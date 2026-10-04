package io.github.jessedeoliveira.playlistorg.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TrackDTO {

    @NotBlank(message = "YouTube track ID is required")
    private String youtubeTrackId;

    @NotBlank(message = "Title is required")
    private String title;

    private String artist;
    private String album;
}
