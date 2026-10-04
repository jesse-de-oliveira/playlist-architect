package io.github.jessedeoliveira.playlistorg.model;

public enum JobStatus {
    PENDING,          // Job created, waiting for Python worker to pick it up
    FETCHING,         // Python worker is pulling tracks from YouTube
    PROCESSING,       // Java heuristic engine is sorting genres
    AWAITING_USER,    // Conflicts found; waiting for user resolution on frontend
    COMPLETED,        // All tracks sorted & exported back to YouTube
    FAILED            // An error occurred during processing
}
