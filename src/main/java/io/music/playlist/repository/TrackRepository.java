package io.music.playlist.repository;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */

@Repository
public class TrackRepository {
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/");
    private static final String CLIENT_ID = "b63ea6a65fe9428a8289c808ae67e945";
    private static final String CLIENT_SECRET = "8186dc39400b4cfba0815003d13766a1";
    private static final String code = "playlisto";

    private final WebClient.Builder webClientBuilder;

    public TrackRepository(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .setRedirectUri(redirectUri)
            .build();

    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
            .build();

    private static void authorizationCode_Sync() {
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public Paging<Track> findTrackByExample(Track track) {
        authorizationCode_Sync();
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(track.getName())
                .build();
        Paging<Track> trackPaging = null;
        try {
            trackPaging = searchTracksRequest.execute();
            System.out.println("Total: " + trackPaging.getTotal());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return trackPaging;
    }

    public Optional<Track> findOne(@NotNull String id) {
        authorizationCode_Sync();
        GetTrackRequest trackRequest = spotifyApi.getTrack(id).build();
        Optional<Track> track = null;
        try {
            track = Optional.of(trackRequest.execute());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return track;
    }
}
