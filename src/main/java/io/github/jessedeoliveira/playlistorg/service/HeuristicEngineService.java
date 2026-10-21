package io.github.jessedeoliveira.playlistorg.service;


import io.github.jessedeoliveira.playlistorg.engine.GenreSorter;
import io.github.jessedeoliveira.playlistorg.model.Track;
import io.github.jessedeoliveira.playlistorg.repository.TrackRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeuristicEngineService {

    private final List<GenreSorter> genreSorters;
    private final TrackRepository trackRepository;

    @Transactional
    public void processPendingTracks(UUID jobId) {
        List<Track> tracks = trackRepository.findBySortingJobId(jobId);

        for (Track track : tracks) {
            for (GenreSorter sorter : genreSorters) {
                boolean wasSorted = sorter.sort(track);

                if (wasSorted) {
                    break;
                }
            }
        }
        trackRepository.saveAll(tracks);
    }

}
