package com.watchapedia.watchpedia_user.model.dto.content;

import com.watchapedia.watchpedia_user.model.entity.content.Movie;
import com.watchapedia.watchpedia_user.model.entity.content.ajax.Star;

import java.util.List;
import java.util.stream.Collectors;

public record MovieDto(
        Long movIdx,
        String movThumbnail,
        String movTitle,
        String movTitleOrg,
        String movMakingDate,
        String movCountry,
        String movGenre,
        String movTime,
        String movAge,
        String movPeople,
        String movSummary,
        String movGallery,
        String movVideo,
        String movWatch,
        String movBackImg,
        List<Star> starList,
        double avg
) {

    public static MovieDto of(
            Long tvIdx, String tvThumbnail, String tvTitle, String tvTitleOrg,
            String tvMakingDate, String tvChannel, String tvGenre,
            String tvCountry, String tvAge, String tvPeople, String tvSummary,
            String tvWatch, String tvGallery, String tvVideo,
            String tvBackImg, List<Star> starList, double avg
    ) {

        return new MovieDto(tvIdx, tvThumbnail, tvTitle, tvTitleOrg,
                tvMakingDate, tvChannel, tvGenre, tvCountry, tvAge,
                tvPeople, tvSummary, tvWatch, tvGallery, tvVideo, tvBackImg, starList, avg
        );
    }

    public static MovieDto of(
            Long movIdx, String movThumbnail, String movTitle,
            String movMakingDate, String movCountry
    ){
        return new MovieDto(
                movIdx,movThumbnail,movTitle,null,movMakingDate,movCountry,
                null, null, null, null, null,
                null, null, null, null, null, 0.0
        );
    }
    public static MovieDto from(Movie entity){
        return new MovieDto(
                entity.getMovIdx(),
                entity.getMovThumbnail(),
                entity.getMovTitle(),
                entity.getMovTitleOrg(),
                entity.getMovMakingDate(),
                entity.getMovCountry(),
                entity.getMovGenre(),
                entity.getMovTime(),
                entity.getMovAge(),
                entity.getMovPeople(),
                entity.getMovSummary(),
                entity.getMovGallery(),
                entity.getMovVideo(),
                entity.getMovWatch(),
                entity.getMovBackImg(),
                entity.getStar(),
                0.0
        );
    }

    public static MovieDto from(Movie entity, double avg){
        return new MovieDto(
                entity.getMovIdx(),
                entity.getMovThumbnail(),
                entity.getMovTitle(),
                entity.getMovTitleOrg(),
                entity.getMovMakingDate(),
                entity.getMovCountry(),
                entity.getMovGenre(),
                entity.getMovTime(),
                entity.getMovAge(),
                entity.getMovPeople(),
                entity.getMovSummary(),
                entity.getMovGallery(),
                entity.getMovVideo(),
                entity.getMovWatch(),
                entity.getMovBackImg(),
                entity.getStar(),
                avg
        );
    }
    public static MovieDto fromis(Movie entity){
        return new MovieDto(
                entity.getMovIdx(),
                entity.getMovThumbnail(),
                entity.getMovTitle(),
                entity.getMovTitleOrg(),
                entity.getMovMakingDate(),
                entity.getMovCountry(),
                entity.getMovGenre(),
                entity.getMovTime(),
                entity.getMovAge(),
                entity.getMovPeople(),
                entity.getMovSummary(),
                entity.getMovGallery(),
                entity.getMovVideo(),
                entity.getMovWatch(),
                entity.getMovBackImg(),
                (List<Star>) entity.getStar().stream().map(star -> {
                    if(star.getStarContentIdx() == entity.getMovIdx()
                            && star.getStarContentType().equals("영화")
                    ){
                        return star;
                    }
                    else{
                        return null;
                    }
                }).collect(Collectors.toList()),
                0
        );
    }
}
