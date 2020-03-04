package io.music.playlist.web.rest;

import io.music.playlist.service.TrackService;
import io.music.playlist.service.PlayListService;
import io.music.playlist.service.dto.PlayListDTO;
import io.music.playlist.web.util.UrlConstants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */
@RestController
@RequestMapping(UrlConstants.GLOBAL_URL)
public class UserPlayListInfoResource {
    private final Logger log = LoggerFactory.getLogger(TrackResource.class);

    private final TrackService trackService;
    private final PlayListService playListService;

    public UserPlayListInfoResource(TrackService trackService, PlayListService playListService) {
        this.trackService = trackService;
        this.playListService = playListService;
    }

    /**
     * GET  /playlist/:userId : getting all playList of one user.
     *
     * @param userId the user id for getting playlist data
     * @return the ResponseEntity with status 200 (OK) and the list of playlists in body
     */
    @PostMapping(UrlConstants.PLAYLIST_URL + "/info/{userId}")
    @ApiOperation(value = "Load all users playlists with all item and track details",
            notes = "This method use of Spotify’s API to retrieve each playlist item data")
    public List<PlayListDTO> getUserPlayListByUserId(@ApiParam(value = "The id of user", required = true) @PathVariable Long userId) {
        log.debug("REST request to get all playlist of a user");
        List<PlayListDTO> result = playListService.findAll(userId);
        return result.stream().peek(playListDTO ->
                playListDTO.setPlayListItems(playListDTO.getPlayListItems().stream().peek(playListItemDTO ->
                        playListItemDTO.setTrackDTO(
                                trackService.findOne(playListItemDTO.getTrackId()).orElse(null)
                        )
                ).collect(Collectors.toList()))).collect(Collectors.toList()
        );
    }
}
