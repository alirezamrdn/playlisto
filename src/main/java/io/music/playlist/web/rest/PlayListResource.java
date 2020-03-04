package io.music.playlist.web.rest;

import io.music.playlist.service.PlayListService;
import io.music.playlist.service.dto.PlayListDTO;
import io.music.playlist.web.errors.BadRequestAlertException;
import io.music.playlist.web.util.HeaderUtil;
import io.music.playlist.web.util.ResponseUtil;
import io.music.playlist.web.util.UrlConstants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */
@RestController
@RequestMapping(UrlConstants.GLOBAL_URL)
public class PlayListResource {
    private final Logger log = LoggerFactory.getLogger(PlayListResource.class);

    private static final String ENTITY_NAME = "playlist";

    private final PlayListService playListService;

    public PlayListResource(PlayListService playListService) {
        this.playListService = playListService;
    }

    /**
     * POST  /playlist : Create a new playlist.
     *
     * @param playListDTO the playListDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new PlayListDTO, or with status 400 (Bad Request) if the playList has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(UrlConstants.PLAYLIST_URL)
    @ApiOperation(value = "Create music playlist for every user",
            notes = "<b>Note:</b> " +
                    "\n You can use JSON Object like this <b> \n{\n" +
                    "  \"description\": \"playlist desc\",\n" +
                    "  \"name\": \"test playlist\",\n" +
                    "  \"userId\": 1\n" +
                    "} </b>" +
                    "\n the other JSON attributes are not required for the object creation")
    public ResponseEntity<PlayListDTO> createPlayList(@Valid @RequestBody @ApiParam(name = "playList") PlayListDTO playListDTO) throws URISyntaxException {
        log.debug("REST request to save PlayList : {}", playListDTO);
        if (playListDTO.getId() != null) {
            throw new BadRequestAlertException("A new playList cannot already have an ID", ENTITY_NAME, "id exists");
        }
        PlayListDTO result = playListService.save(playListDTO);
        return ResponseEntity.created(new URI(UrlConstants.GLOBAL_URL + UrlConstants.PLAYLIST_URL + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /playlist : Updates an existing playlist.
     *
     * @param playListDTO the playListDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated playListDTO,
     * or with status 400 (Bad Request) if the playList is not valid,
     * or with status 500 (Internal Server Error) if the playList couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping(UrlConstants.PLAYLIST_URL)
    @ApiOperation(value = "Update music playlist for every user",
            notes = "<b>Note:</b> " +
                    "\n You can use JSON Object like this <b> \n{\n" +
                    "  \"description\": \"playlist desc\",\n" +
                    "  \"name\": \"test playlist\",\n" +
                    "  \"userId\": 1\n" +
                    "} </b>" +
                    "\n the other JSON attributes are not required for the object modification")
    public ResponseEntity<PlayListDTO> updatePlayList(@Valid @RequestBody @ApiParam(name = "playList") PlayListDTO playListDTO) throws URISyntaxException {
        log.debug("REST request to update PlayList : {}", playListDTO);
        if (playListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "id");
        }
        PlayListDTO result = playListService.save(playListDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, playListDTO.getId().toString()))
                .body(result);
    }


    /**
     * GET  /playlist/:userId : get all the playlists.
     *
     * @param userId the id of user to retrieving all playlist
     * @return the ResponseEntity with status 200 (OK) and the list of playlists in body
     */
    @GetMapping(UrlConstants.PLAYLIST_URL + "/{userId}")
    public List<PlayListDTO> getAllPlaylistsByUserId(@PathVariable Long userId) {
        log.debug("REST request to get all Playlists");
        return playListService.findAll(userId);
    }

    /**
     * GET  /playlist/:userId/:playlistId : get the playlist by userId and playlistId.
     *
     * @param userId the id of user to retrieving all playlist
     * @param playlistId the id of the playlist to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the playlist, or with status 404 (Not Found)
     */
    @GetMapping(UrlConstants.PLAYLIST_URL + "/{userId}/{playlistId}")
    public ResponseEntity<PlayListDTO> getOnePlaylistByUserAndPlaylistId(@PathVariable Long userId, @PathVariable Long playlistId) {
        log.debug("REST request to get Playlist : {}", playlistId);
        Optional<PlayListDTO> playlistDTO = playListService.findOne(userId, playlistId);
        return ResponseUtil.wrapOrNotFound(playlistDTO);
    }

    /**
     * DELETE  /playlist/:userId/:playlistId : delete the playlist by userId and playlistId.
     *
     * @param userId the id of user
     * @param playlistId the id of the playlist to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping(UrlConstants.PLAYLIST_URL + "/{userId}/{playlistId}")
    public ResponseEntity<Void> deletePlaylistByUserIdAndPlaylistId(@PathVariable Long userId, @PathVariable Long playlistId) {
        log.debug("REST request to delete Playlist : {}", playlistId);
        playListService.remove(userId, playlistId);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, playlistId.toString())).build();
    }
}
