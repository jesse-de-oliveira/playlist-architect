package io.github.jessedeoliveira.playlistorg.engine;

import io.github.jessedeoliveira.playlistorg.model.Track;
import io.github.jessedeoliveira.playlistorg.model.TrackStatus;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@Order(2)
public class ExternalApiGenreSorter implements GenreSorter {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String ITUNES_API_URL = "https://itunes.apple.com/search";

    @Override
    public boolean sort(Track track) {

        String searchTerm = buildSearchTerm(track);
        if (searchTerm.isBlank()) {
            return false;
        }

        URI uri = UriComponentsBuilder.fromUriString(ITUNES_API_URL)
                .queryParam("term", searchTerm)
                .queryParam("entity", "song")
                .queryParam("limit", 1)
                .build()
                .toUri();
        try {
            ItunesResponse response = restTemplate.getForObject(uri, ItunesResponse.class);

            if (response != null && response.results() != null && !response.results().isEmpty()) {
                String fetchedGenre = response.results().get(0).primaryGenreName();

                track.setResolvedGenre(fetchedGenre);
                track.setStatus(TrackStatus.RESOLVED);

                Thread.sleep(150);

                return true;
            }
        } catch (Exception e) {
            System.err.println("iTunes API failed for track: " + track.getTitle() + " - " + e.getMessage());
        }
        return false;
    }

    private String buildSearchTerm(Track track) {
        if (track.getArtist() != null && !track.getArtist().isBlank()) {
            return track.getArtist() + " " + track.getTitle();
        }
        return track.getTitle();
    }

    record ItunesResponse(List<ItunesResult> results) {}
    record ItunesResult(String primaryGenreName) {}
}
