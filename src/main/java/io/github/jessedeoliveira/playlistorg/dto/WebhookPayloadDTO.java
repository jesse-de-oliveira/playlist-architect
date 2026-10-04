package io.github.jessedeoliveira.playlistorg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class WebhookPayloadDTO {

    @NotBlank(message = "Job ID is required")
    private String jobId;

    @NotBlank(message = "Source Playlist ID is required")
    private String sourcePlaylistId;

    @NotBlank(message = "Status is required")
    private String status;

    @NotEmpty(message = "Track list cannot be empty")
    @Valid
    private List<TrackDTO> tracks;
}
