package io.music.playlist.web.rest;

import io.music.playlist.service.TrackService;
import io.music.playlist.service.dto.TrackDTO;
import io.music.playlist.web.util.UrlConstants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */
@RestController
@RequestMapping(UrlConstants.GLOBAL_URL)
public class TrackResource {
    private final Logger log = LoggerFactory.getLogger(TrackResource.class);

    private final TrackService trackService;

    public TrackResource(TrackService trackService) {
        this.trackService = trackService;
    }

    /**
     * GET  /search : searching music by criteria.
     *
     * @param trackDTO the criteria for searching music
     * @return the ResponseEntity with status 200 (OK) and the list of playlists in body
     */
    @PostMapping(UrlConstants.SEARCH_URL)
    @ApiOperation(value = "Find music by name and artistName",
            notes = "This method use of Spotify’s API to retrieve data.\n " +
                    "<b>Note:</b> " +
                    "\n You can use JSON Object like this <b> \n { \n " +
                    "\"name\": \"string\" \n} </b>" +
                    "\n the other JSON attributes are not implemented in the search criteria")
    public List<TrackDTO> findMusicByCriteria(@Valid @RequestBody
                                                    @ApiParam(name = "trackQuery",
                                                            required = true,
                                                            value = "The query for searching music")
                                                      TrackDTO trackDTO) {
        log.debug("REST request to search musics");
        return trackService.findTrackByExample(trackDTO);
    }
}
