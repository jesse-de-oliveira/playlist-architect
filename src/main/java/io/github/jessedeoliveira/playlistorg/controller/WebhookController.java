package io.github.jessedeoliveira.playlistorg.controller;

import io.github.jessedeoliveira.playlistorg.dto.WebhookPayloadDTO;
import io.github.jessedeoliveira.playlistorg.service.WebhookIngestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookIngestionService webhookIngestionService;

    @PostMapping("/youtube")
    public ResponseEntity<Void> receiveYoutubeData(@Valid @RequestBody WebhookPayloadDTO payload) {

        webhookIngestionService.processPlaylistWebhook(payload);

        return ResponseEntity.accepted().build();
    }
}
