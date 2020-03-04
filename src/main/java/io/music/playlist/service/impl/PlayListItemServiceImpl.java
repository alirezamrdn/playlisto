package io.music.playlist.service.impl;

import io.music.playlist.repository.PlayListItemRepository;
import io.music.playlist.service.PlayListItemService;
import io.music.playlist.service.PlayListService;
import io.music.playlist.service.dto.PlayListDTO;
import io.music.playlist.service.dto.PlayListItemDTO;
import io.music.playlist.exception.ErrorCodes;
import io.music.playlist.exception.PlayListNotFoundException;
import io.music.playlist.service.mapper.PlayListItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */

@Service
@Transactional
public class PlayListItemServiceImpl implements PlayListItemService {

    private final Logger log = LoggerFactory.getLogger(PlayListItemServiceImpl.class);

    private final PlayListItemRepository playListItemRepository;

    private final PlayListService playListService;

    private final PlayListItemMapper playListItemMapper;

    public PlayListItemServiceImpl(PlayListService playListService, PlayListItemRepository playListItemRepository, PlayListItemMapper playListItemMapper) {
        this.playListItemRepository = playListItemRepository;
        this.playListItemMapper = playListItemMapper;
        this.playListService = playListService;
    }

    @Override
    public PlayListItemDTO add(Long userId, PlayListItemDTO playListItemDTO) throws PlayListNotFoundException {
        log.debug("Request to add playlistItem : {}", playListItemDTO);
        Optional<PlayListDTO> playListDTO = playListService.findOne(userId, playListItemDTO.getPlayListId());
        if(playListDTO.isPresent()) {
            return playListItemMapper.toDto(
                    playListItemRepository.save(
                            playListItemMapper.toEntity(playListItemDTO)
                    )
            );
        } else {
            throw new PlayListNotFoundException("There isn't any playlist to adding items", ErrorCodes.NOT_FOUND);
        }
    }

    @Override
    public void remove(Long userId, Long playListId, Long trackId) throws PlayListNotFoundException {
        Optional<PlayListDTO> playListDTO = playListService.findOne(userId, playListId);
        if(playListDTO.isPresent()) {
            log.debug("Request to remove playlistId : {}", playListId);
            playListItemRepository.deleteByPlaylistIdAndtrackId(playListDTO.get().getId(), trackId);
        } else {
            throw new PlayListNotFoundException("There isn't any playlist to removing items", ErrorCodes.NOT_FOUND);
        }
    }
}
