package io.github.jessedeoliveira.playlistorg.engine;

import io.github.jessedeoliveira.playlistorg.model.Track;

public interface GenreSorter {

    /**
     * Attempts to resolve and assign a genre to the track.
     *
     * @param track The raw track from the database.
     * @return true if this link in the chain successfully sorted the track,
     *         false if it failed and the track should be passed to the next link.
     */

    boolean sort(Track track);
}
