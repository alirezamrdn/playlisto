package io.music.playlist.service.impl;

import io.music.playlist.repository.TrackRepository;
import io.music.playlist.service.TrackService;
import io.music.playlist.service.dto.TrackDTO;
import io.music.playlist.service.mapper.TrackMapperImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;

    private final TrackMapperImpl trackMapper;

    public TrackServiceImpl(TrackRepository trackRepository, TrackMapperImpl trackMapper) {
        this.trackRepository = trackRepository;
        this.trackMapper = trackMapper;
    }

    @Override
    public List<TrackDTO> findTrackByExample(TrackDTO trackDTO) {
        return Arrays.stream(trackRepository.findTrackByExample(trackMapper.toEntity(trackDTO)).getItems()).
                map(trackMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<TrackDTO> findOne(String id) {
        return trackRepository.findOne(id).map(trackMapper::toDto);
    }
}
