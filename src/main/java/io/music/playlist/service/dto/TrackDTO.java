package io.music.playlist.service.dto;

import io.swagger.annotations.ApiModel;

import java.time.Instant;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1 $
 */
@ApiModel(value = "Track",description = "This model is for getting data from Spotify's API.\n" +
        "This also is used for querying the data from the API")
public class TrackDTO {

     private String id;
     private String name;
     private String href;
     private Integer durationMs;

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getHref() {
          return href;
     }

     public void setHref(String href) {
          this.href = href;
     }

     public Integer getDurationMs() {
          return durationMs;
     }

     public void setDurationMs(Integer durationMs) {
          this.durationMs = durationMs;
     }
}
