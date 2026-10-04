package io.github.jessedeoliveira.playlistorg.model;

public enum TrackStatus {
    PENDING,   // Raw track imported, awaiting sorting logic
    RESOLVED,  // Genre definitively assigned (auto or manual)
    CONFLICT   // Multiple genre tags matched; needs user or secondary resolution
}
