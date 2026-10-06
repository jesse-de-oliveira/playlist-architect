package io.github.jessedeoliveira.playlistorg.service;

import io.github.jessedeoliveira.playlistorg.dto.TrackDTO;
import io.github.jessedeoliveira.playlistorg.dto.WebhookPayloadDTO;
import io.github.jessedeoliveira.playlistorg.model.JobStatus;
import io.github.jessedeoliveira.playlistorg.model.SortingJob;
import io.github.jessedeoliveira.playlistorg.model.Track;
import io.github.jessedeoliveira.playlistorg.model.TrackStatus;
import io.github.jessedeoliveira.playlistorg.repository.SortingJobRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WebhookIngestionService {

    private final SortingJobRepository sortingJobRepository;

    @Transactional
    public void processPlaylistWebhook(WebhookPayloadDTO payload) {

        // 1. Convert the String ID back to a UUID and find the pending job
        UUID jobId = UUID.fromString(payload.getJobId());
        SortingJob job = sortingJobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Sorting Job not found: " + jobId));

        // 2. Handle failure states gracefully
        if (!"FETCH_SUCCESS".equals(payload.getStatus())) {
            job.setStatus(JobStatus.FAILED);
            sortingJobRepository.save(job);
            return;
        }

        // 3. Map the DTOs to Entities
        for (TrackDTO trackDTO : payload.getTracks()) {
            Track track = Track.builder()
                    .youtubeTrackId(trackDTO.getYoutubeTrackId())
                    .title(trackDTO.getTitle())
                    .artist(trackDTO.getArtist())
                    .album(trackDTO.getAlbum())
                    .status(TrackStatus.PENDING)
                    .sortingJob(job)
                    .build();

            job.getTracks().add(track);
        }

        job.setStatus(JobStatus.PROCESSING);

        sortingJobRepository.save(job);
    }
}
