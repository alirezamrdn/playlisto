package io.music.playlist.service.dto;

import io.swagger.annotations.ApiModel;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */
@ApiModel(value = "PlayListItem",description = "This model is for saving track in playlist.")
public class PlayListItemDTO {

    private Long id;
    private Long playListId;
    private String trackId;
    private TrackDTO trackDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayListId() {
        return playListId;
    }

    public void setPlayListId(Long playListId) {
        this.playListId = playListId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public TrackDTO getTrackDTO() {
        return trackDTO;
    }

    public void setTrackDTO(TrackDTO trackDTO) {
        this.trackDTO = trackDTO;
    }
}
