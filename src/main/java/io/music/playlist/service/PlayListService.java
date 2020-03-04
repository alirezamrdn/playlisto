package io.music.playlist.service;

import io.music.playlist.service.dto.PlayListDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */

public interface PlayListService {
    Optional<PlayListDTO> findOne(Long userId, Long playListId);
    Optional<PlayListDTO> findOne(Long playListId);
    List<PlayListDTO> findAll(Long userId);
    List<PlayListDTO> findByExample(PlayListDTO playListDTO);
    PlayListDTO save(PlayListDTO playListDTO);
    void remove(Long userId, Long playListId);
}
