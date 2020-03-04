package io.music.playlist.service.impl;

import io.music.playlist.domain.PlayList;
import io.music.playlist.repository.PlayListItemRepository;
import io.music.playlist.repository.PlayListRepository;
import io.music.playlist.service.PlayListService;
import io.music.playlist.service.dto.PlayListDTO;
import io.music.playlist.service.mapper.PlayListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */
@Service
@Transactional
public class PlayListServiceImpl implements PlayListService {

    private final Logger log = LoggerFactory.getLogger(PlayListServiceImpl.class);

    private final PlayListRepository playListRepository;

    private final PlayListItemRepository playListItemRepository;

    private final PlayListMapper playListMapper;

    public PlayListServiceImpl(PlayListRepository playListRepository, PlayListMapper playListMapper, PlayListItemRepository playListItemRepository) {
        this.playListRepository = playListRepository;
        this.playListMapper = playListMapper;
        this.playListItemRepository = playListItemRepository;
    }

    @Override
    public Optional<PlayListDTO> findOne(Long userId, Long playListId) {
        PlayList playList = new PlayList(userId, playListId);
        log.debug("Request to get playlist by criteria : {}", playList);
        return playListRepository.findOne(
                Example.of(playList)
        ).map(playListMapper::toDto);
    }

    @Override
    public Optional<PlayListDTO> findOne(Long playListId) {
        PlayList playList = new PlayList();
        playList.setId(playListId);
        log.debug("Request to get playlist by criteria : {}", playList);
        return playListRepository.findOne(
                Example.of(playList)
        ).map(playListMapper::toDto);
    }


    @Override
    public List<PlayListDTO> findAll(Long userId) {
        PlayList playList = new PlayList(userId);
        log.debug("Request to get playlist by criteria : {}", playList);
        return playListMapper.toDto(
                playListRepository.findAll(
                        Example.of(playList)
                )
        );
    }

    @Override
    public List<PlayListDTO> findByExample(PlayListDTO playListDTO) {
        return playListMapper.toDto(
                playListRepository.findAll(
                        Example.of(
                                playListMapper.toEntity(playListDTO)
                        )
                )
        );
    }

    @Override
    public PlayListDTO save(PlayListDTO playListDTO) {
        log.debug("Request to save playlist : {}", playListDTO);
        return playListMapper.toDto(
                playListRepository.save(
                        playListMapper.toEntity(playListDTO)
                )
        );
    }

    @Override
    public void remove(Long userId, Long playListId) {
        PlayList playList = new PlayList(playListId, userId);
        log.debug("Request to remove playlist : {}", playListId);
        playListItemRepository.deleteAllPlayListItems(playListId);
        playListRepository.delete(playList);
    }
}
