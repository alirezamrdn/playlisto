package io.music.playlist.web.rest;

import io.music.playlist.service.PlayListItemService;
import io.music.playlist.service.dto.PlayListItemDTO;
import io.music.playlist.web.util.HeaderUtil;
import io.music.playlist.web.util.UrlConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */
@RestController
@RequestMapping(UrlConstants.GLOBAL_URL)
public class PlayListItemResource {
    private final Logger log = LoggerFactory.getLogger(PlayListItemResource.class);

    private static final String ENTITY_NAME = "playlistItem";

    private final PlayListItemService playListItemService;

    public PlayListItemResource(PlayListItemService playListItemService) {
        this.playListItemService = playListItemService;
    }

    /**
     * POST  /playlistItem : Add an track to a playlist.
     *
     * @param userId the id of user
     * @param playlistId the id of the playlist
     * @param trackId the id of the track to adding to a playList
     * @return the ResponseEntity with status 201 (Created) and with body the new PlayListItemDTO, or with status 400 (Bad Request) if the playListItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(UrlConstants.PLAYLIST_ITEM_URL + "/{userId}/{playlistId}/{trackId}")
    public ResponseEntity<PlayListItemDTO> addtrackToPlayList(@PathVariable Long userId, @PathVariable Long playlistId, @PathVariable String trackId) throws URISyntaxException {
        log.debug("REST request to save PlayListItem : {}", playlistId);
        PlayListItemDTO playListItemDTO = new PlayListItemDTO();
        playListItemDTO.setTrackId(trackId);
        playListItemDTO.setPlayListId(playlistId);
        PlayListItemDTO result = playListItemService.add(userId, playListItemDTO);
        return ResponseEntity.created(new URI(UrlConstants.GLOBAL_URL + UrlConstants.PLAYLIST_ITEM_URL + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * DELETE  /playlistItem/:userId/:playlistId/:trackId : delete the playlistItem by userId, playlistId, and trackId.
     *
     * @param userId the id of user
     * @param playlistId the id of the playlist
     * @param trackId the id of the track to deleting from a playList
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping(UrlConstants.PLAYLIST_ITEM_URL + "/{userId}/{playlistId}/{trackId}")
    public ResponseEntity<Void> removetrackFromPlaylist(@PathVariable Long userId, @PathVariable Long playlistId, @PathVariable Long trackId) {
        log.debug("REST request to delete PlayListItem : {}", playlistId);
        playListItemService.remove(userId, playlistId, trackId);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, playlistId.toString())).build();
    }
}
