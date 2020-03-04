package io.music.playlist.service;

import io.music.playlist.service.dto.TrackDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */

public interface TrackService {
    List<TrackDTO> findTrackByExample(TrackDTO trackDTO);
    Optional<TrackDTO> findOne(String id);
}
