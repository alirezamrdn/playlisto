package io.music.playlist;

import io.music.playlist.web.filter.UrlFilter;
import io.music.playlist.web.util.UrlConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class MusicPlayListApplication {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        UrlFilter urlFilter = new UrlFilter();
        filterRegistrationBean.setFilter(urlFilter);
        return filterRegistrationBean;
    }

    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant(UrlConstants.GLOBAL_URL + "/**"))
                .build().apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("Spotify Playlist Sample",
                "This project is a music playlist service where user can store and retrieve a " +
                        "number of music tracks. The service will make use of Spotify’s API to retrieve " +
                        "the information about tracks, albums, artists, etc",
                "0.0.1",
                "https://github.com/alirezamrdn/playlisto",
                new Contact("Alireza Mardani",
                        "https://www.linkedin.com/in/alireza-mardani-kamali",
                        "alirezamardani@gmail.com"),
                "Spotify License",
                "https://github.com/alirezamrdn/playlisto"
                , Collections.emptyList());
    }

    public static void main(String[] args) {
        SpringApplication.run(MusicPlayListApplication.class, args);
    }

}
