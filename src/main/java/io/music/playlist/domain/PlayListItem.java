package io.music.playlist.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */
@Entity
@Table(name = "tbl_play_list_item")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PlayListItem implements Serializable {

    public PlayListItem() {
    }

    public PlayListItem(Long playListId, String trackId) {
        this.trackId = trackId;
        PlayList playList = new PlayList();
        playList.setId(playListId);
        this.playList = playList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("")
    private PlayList playList;

    @NotNull
    @Column(name = "track_Id", nullable = false)
    private String trackId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayList getPlayList() {
        return playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayListItem that = (PlayListItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(playList, that.playList) &&
                Objects.equals(trackId, that.trackId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (playList != null ? playList.hashCode() : 0);
        result = 31 * result + (trackId != null ? trackId.hashCode() : 0);
        return result;
    }
}
