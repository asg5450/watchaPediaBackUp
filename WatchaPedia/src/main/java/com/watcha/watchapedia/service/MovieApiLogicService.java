package com.watcha.watchapedia.service;

import com.watcha.watchapedia.model.entity.Movie;
import com.watcha.watchapedia.model.network.Header;
import com.watcha.watchapedia.model.network.request.MovieApiRequest;
import com.watcha.watchapedia.model.network.response.MovieApiResponse;
import com.watcha.watchapedia.model.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class MovieApiLogicService extends BaseService<MovieApiRequest, MovieApiResponse, Movie>{
    private final MovieRepository movieRepository;

    private MovieApiResponse response(Movie movie){
        MovieApiResponse movieApiResponse = MovieApiResponse.builder()
                .movIdx(movie.getMovIdx())
                .movThumbnail(movie.getMovThumbnail())
                .movTitle(movie.getMovTitle())
                .movTitleOrg(movie.getMovTitleOrg())
                .movMakingDate(movie.getMovMakingDate())
                .movCountry(movie.getMovCountry())
                .movGenre(movie.getMovGenre())
                .movTime(movie.getMovTime())
                .movAge(movie.getMovAge())
                .movPeople(movie.getMovPeople())
                .movSummary(movie.getMovSummary())
                .movGallery(movie.getMovGallery())
                .movVideo(movie.getMovVideo())
                .movWatch(movie.getMovWatch())
                .movBackImg(movie.getMovBackImg())
                .build();
        return movieApiResponse;
    }

    @Override
    public Header<MovieApiResponse> create(Header<MovieApiRequest> request) {
        MovieApiRequest movieApiRequest = request.getData();
        Movie movie = Movie.builder()
                .movThumbnail(movieApiRequest.getMovThumbnail())
                .movTitle(movieApiRequest.getMovTitle())
                .movTitleOrg(movieApiRequest.getMovTitleOrg())
                .movMakingDate(movieApiRequest.getMovMakingDate())
                .movCountry(movieApiRequest.getMovCountry())
                .movGenre(movieApiRequest.getMovGenre())
                .movTime(movieApiRequest.getMovTime())
                .movAge(movieApiRequest.getMovAge())
                .movPeople(movieApiRequest.getMovPeople())
                .movSummary(movieApiRequest.getMovSummary())
                .movGallery(movieApiRequest.getMovGallery())
                .movVideo(movieApiRequest.getMovVideo())
                .movWatch(movieApiRequest.getMovWatch())
                .movBackImg(movieApiRequest.getMovBackImg())
                .build();
        Movie newMovie = baseRepository.save(movie);
        return Header.OK(response(newMovie));
    }



    @Override
    public Header<MovieApiResponse> read(Long id) {
        return baseRepository.findById(id).map(movie -> response(movie))
                .map(Header::OK).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<MovieApiResponse> update(Header<MovieApiRequest> request) {
        MovieApiRequest movieApiRequest = request.getData();
        Optional<Movie> movies = movieRepository.findByMovIdx(movieApiRequest.getMovIdx());
        System.out.println(movies);
        return movies.map(
                        movie -> {
                            movie.setMovThumbnail(movieApiRequest.getMovThumbnail());
                            movie.setMovTitle(movieApiRequest.getMovTitle());
                            movie.setMovTitleOrg(movieApiRequest.getMovTitleOrg());
                            movie.setMovMakingDate(movieApiRequest.getMovMakingDate());
                            movie.setMovCountry(movieApiRequest.getMovCountry());
                            movie.setMovGenre(movieApiRequest.getMovGenre());
                            movie.setMovTime(movieApiRequest.getMovTime());
                            movie.setMovAge(movieApiRequest.getMovAge());
                            movie.setMovPeople(movieApiRequest.getMovPeople());
                            movie.setMovSummary(movieApiRequest.getMovSummary());
                            movie.setMovGallery(movieApiRequest.getMovGallery());
                            movie.setMovVideo(movieApiRequest.getMovVideo());
                            movie.setMovWatch(movieApiRequest.getMovWatch());
                            movie.setMovBackImg(movieApiRequest.getMovBackImg());
                            return movie;
                        }).map(movie -> movieRepository.save(movie))
                .map(movie -> response(movie))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header delete(Long movIdx) {
        Optional<Movie> movie = baseRepository.findById(movIdx);
        return movie.map(movie1 -> {
            baseRepository.delete(movie1);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    public Header<List<MovieApiResponse>> search(){
        List<Movie> movies = baseRepository.findAll();
        List<MovieApiResponse> movieApiResponse = movies.stream().map(
                movie -> response(movie)).collect(Collectors.toList());


        return Header.OK(movieApiResponse);
    }


    public List<Movie> movieList(){
        return movieRepository.findAll();
    }
}

