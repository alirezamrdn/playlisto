package io.music.playlist.service;

import io.music.playlist.service.dto.PlayListItemDTO;
import io.music.playlist.exception.PlayListNotFoundException;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */


public interface PlayListItemService {

    PlayListItemDTO add(Long userId, PlayListItemDTO playListItemDTO) throws PlayListNotFoundException;
    void remove(Long userId, Long playListId, Long trackId) throws PlayListNotFoundException;
}
