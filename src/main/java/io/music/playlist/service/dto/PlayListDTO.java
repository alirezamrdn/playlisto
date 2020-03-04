package io.music.playlist.service.dto;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */
@ApiModel(value = "PlayList",description = "This Object is for saving playlist information for every user")
public class PlayListDTO {

    private Long id;
    private Long userId;
    private String name;
    private String description;
    private List<PlayListItemDTO> playListItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PlayListItemDTO> getPlayListItems() {
        return playListItems;
    }

    public void setPlayListItems(List<PlayListItemDTO> playListItems) {
        this.playListItems = playListItems;
    }
}
