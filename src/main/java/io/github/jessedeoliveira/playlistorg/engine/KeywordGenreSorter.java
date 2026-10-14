package io.github.jessedeoliveira.playlistorg.engine;

import io.github.jessedeoliveira.playlistorg.model.Track;
import io.github.jessedeoliveira.playlistorg.model.TrackStatus;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Order(1)
public class KeywordGenreSorter implements GenreSorter {

    private static final Map<String, String> GENRE_KEYWORDS = new java.util.LinkedHashMap<>();

    static {
        // 1. Highly Specific Sub-Genres & Niche Terms (Check these first)
        // Global / Regional
        GENRE_KEYWORDS.put("kpop", "K-Pop");
        GENRE_KEYWORDS.put("k-pop", "K-Pop");
        GENRE_KEYWORDS.put("jpop", "J-Pop");
        GENRE_KEYWORDS.put("j-pop", "J-Pop");
        GENRE_KEYWORDS.put("celtic", "Cinematic / Folk");
        GENRE_KEYWORDS.put("gaelic", "Cinematic / Folk");
        GENRE_KEYWORDS.put("wuxia", "Soundtrack");
        GENRE_KEYWORDS.put("afrobeats", "Afrobeats");
        GENRE_KEYWORDS.put("amapiano", "Amapiano");
        GENRE_KEYWORDS.put("reggaeton", "Reggaeton");
        GENRE_KEYWORDS.put("bollywood", "Bollywood");
        GENRE_KEYWORDS.put("bachata", "Latin");
        GENRE_KEYWORDS.put("salsa", "Latin");
        GENRE_KEYWORDS.put("mariachi", "Latin");

        // Brazilian Genres (Specific)
        GENRE_KEYWORDS.put("mpb", "MPB");
        GENRE_KEYWORDS.put("samba", "Samba");
        GENRE_KEYWORDS.put("bossa nova", "Bossa Nova");
        GENRE_KEYWORDS.put("sertanejo", "Sertanejo");
        GENRE_KEYWORDS.put("pagode", "Pagode");
        GENRE_KEYWORDS.put("forro", "Forró");
        GENRE_KEYWORDS.put("forró", "Forró");

        // Pop Modifiers
        GENRE_KEYWORDS.put("synthpop", "Electronic / Pop");
        GENRE_KEYWORDS.put("electropop", "Electronic / Pop");
        GENRE_KEYWORDS.put("dreampop", "Indie / Pop");

        // Electronic Sub-genres
        GENRE_KEYWORDS.put("edm", "EDM");
        GENRE_KEYWORDS.put("house", "Electronic / House");
        GENRE_KEYWORDS.put("techno", "Electronic / Techno");
        GENRE_KEYWORDS.put("dubstep", "Electronic / Dubstep");
        GENRE_KEYWORDS.put("trance", "Electronic / Trance");
        GENRE_KEYWORDS.put("lofi", "Lo-Fi");
        GENRE_KEYWORDS.put("lo-fi", "Lo-Fi");

        // 2. Multi-word Genres & Combinations
        // Brazilian Funk (Must be above "funk")
        GENRE_KEYWORDS.put("brazilian funk", "Brazilian Funk");
        GENRE_KEYWORDS.put("funk carioca", "Brazilian Funk");

        // General Multi-word
        GENRE_KEYWORDS.put("hip hop", "Hip Hop & Rap");
        GENRE_KEYWORDS.put("hip-hop", "Hip Hop & Rap");
        GENRE_KEYWORDS.put("hip-hop/rap", "Hip Hop & Rap");
        GENRE_KEYWORDS.put("rnb", "R&B");
        GENRE_KEYWORDS.put("r&b", "R&B");
        GENRE_KEYWORDS.put("alt rock", "Alternative Rock");
        GENRE_KEYWORDS.put("alt-rock", "Alternative Rock");
        GENRE_KEYWORDS.put("alternative rock", "Alternative Rock");
        GENRE_KEYWORDS.put("indie rock", "Indie Rock");
        GENRE_KEYWORDS.put("indie pop", "Indie Pop");
        GENRE_KEYWORDS.put("classical crossover", "Classical Crossover");

        // Specific genres mapped to "Other" (Must be above "soul" and "punk")
        GENRE_KEYWORDS.put("post-punk", "Other");
        GENRE_KEYWORDS.put("pop-punk", "Other");
        GENRE_KEYWORDS.put("neo-soul", "Other");

        // 3. Broad / General Genres (Check these last to avoid swallowing)
        GENRE_KEYWORDS.put("pop", "Pop");
        GENRE_KEYWORDS.put("rap", "Hip Hop & Rap");
        GENRE_KEYWORDS.put("indie", "Indie");
        GENRE_KEYWORDS.put("folk", "Folk");
        GENRE_KEYWORDS.put("funk", "Funk");
        GENRE_KEYWORDS.put("rock", "Rock");
        GENRE_KEYWORDS.put("jazz", "Jazz");
        GENRE_KEYWORDS.put("acoustic", "Acoustic");
        GENRE_KEYWORDS.put("country", "Country");
        GENRE_KEYWORDS.put("alternative", "Alternative");
        GENRE_KEYWORDS.put("electronic", "Electronic");
        GENRE_KEYWORDS.put("classical", "Classical");
        GENRE_KEYWORDS.put("soul", "Soul");
        GENRE_KEYWORDS.put("blues", "Blues");
        GENRE_KEYWORDS.put("latin", "Latin");
        GENRE_KEYWORDS.put("gospel", "Gospel");

        // 4. "Other" Requested Category
        // Catch-all for broad genres you want grouped as "Other"
        GENRE_KEYWORDS.put("heavy metal", "Other");
        GENRE_KEYWORDS.put("nu metal", "Other");
        GENRE_KEYWORDS.put("metal", "Other");
        GENRE_KEYWORDS.put("punk", "Other");
        GENRE_KEYWORDS.put("ambient", "Other");
        GENRE_KEYWORDS.put("disco", "Other");
        GENRE_KEYWORDS.put("reggae", "Other");
    }

    @Override
    public boolean sort(Track track) {
        String combinedText = String.format("%s %s %s", track.getTitle(), track.getArtist(), track.getAlbum()).toLowerCase();

        for (Map.Entry<String, String> entry : GENRE_KEYWORDS.entrySet()) {
            if (combinedText.contains(entry.getKey())) {

                track.setResolvedGenre(entry.getValue());
                track.setStatus(TrackStatus.RESOLVED);

                return true;
            }
        }
        return false;
    }
}
