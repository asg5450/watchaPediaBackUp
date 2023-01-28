package com.watcha.watchapedia.service;

import com.watcha.watchapedia.model.entity.Person;
import com.watcha.watchapedia.model.network.response.PersonApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GlobalMethodService {

    private final MovieApiLogicService movieApiLogicService;
    private final TvApiLogicService tvApiLogicService;
    private final BookApiLogicService bookApiLogicService;
    private final WebtoonApiLogicService webtoonApiLogicService;

    public PersonApiResponse serachResponse(Person person){

        System.out.println(person.getPerName() + "의 정보를 가지고 stream -> map -> response메소드에 진입 성공!");
        //출연 및 연출작 List를 넘겨주기 위한 List 제작 과정(2개)

        List<String> appearMov = new ArrayList<>();
        List<String> appearTv = new ArrayList<>();
        List<String> appearBook = new ArrayList<>();
        List<String> appearWeb = new ArrayList<>();

        List<String> appearFinal = new ArrayList<>();

        //영화 연출작 "," 기준으로 스플릿해서  appearMov에 담기
        if (person.getPerMov() != null) {
            String[] movArr = person.getPerMov().split(",");
            for(String s : movArr){
                appearMov.add(s);
            }
        }

        //Tv 연출작 "," 기준으로 스플릿해서  appearTv에 담기
        if(person.getPerTv() != null){
            String[] tvArr = person.getPerTv().split(",");
            for(String s : tvArr){
                appearTv.add(s);
            }
        }

        //Book 연출작 ","기준으로 스플릿해서 appearBook에 담기
        if(person.getPerBook() != null){
            String[] bookArr = person.getPerBook().split(",");
            for(String s : bookArr){
                appearBook.add(s);
            }
        }

        //Webtoon 연출작 ","기준으로 스플릿해서 appearWeb에 담기
        if(person.getPerWebtoon() != null){
            System.out.println("웹툰 이프문 진입!");
            String[] webArr = person.getPerWebtoon().split(",");
            System.out.println("webArr에 담긴 값들은 : "+webArr);
            for(String s : webArr){
                System.out.println("for문 s의 값 : " + s);
                appearWeb.add(s);
            }
        }

        //영화 연출작, TV 연출작, ... 연출작 갯수 확인
        System.out.println("appearMov.size() : " + appearMov.size());
        System.out.println("appearTv.size() : " + appearTv.size());
        System.out.println("appearBook.size() : " + appearBook.size());
        System.out.println("appearWeb.size() : " + appearWeb.size());


        //appearFinal에 담아주기
        for(String s : appearMov){
            if(appearFinal.size() >= 2){
                break;
            }
            appearFinal.add(movieApiLogicService.read(Long.valueOf(s)).getData().getMovTitle());
        }

        for(String s : appearTv){
            if(appearFinal.size() >= 2){
                break;
            }
            appearFinal.add(tvApiLogicService.read(Long.valueOf(s)).getData().getTvTitle());
        }

        for(String s : appearBook){
            if(appearFinal.size() >= 2){
                break;
            }
            System.out.println("appearBook이 존재해서 appearFinal + 1");
            appearFinal.add(bookApiLogicService.read(Long.valueOf(s)).getData().getBookTitle());
        }

        for(String s : appearWeb){
            if(appearFinal.size() >= 2){
                break;
            }
            System.out.println("appearWeb이 존재해서 appearFinal + 1");
            appearFinal.add(webtoonApiLogicService.read(Long.valueOf(s)).getData().getWebTitle());
        }

        //연출작이 3개가 넘으면 2개만 남기고 지워줌
        while(appearFinal.size() >= 3){
            appearFinal.remove(appearFinal.size()-1);
        }

        //인물사진이 없으면 기본이미지 넣기
        String personSphoto = person.getPerPhoto();
        if(personSphoto == null){
            personSphoto = "data:image/jpeg;base64,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";
        }

        //PersonApiResponse형으로 Person을 변형하는 과정
        PersonApiResponse personApiResponse = PersonApiResponse.builder()
                .perIdx(person.getPerIdx())
                .perName(person.getPerName())
                .perPhoto(personSphoto)
                .perRole(person.getPerRole())
                .appearance(appearFinal)
                .build();
        System.out.println("PersonApiResponse형태로 뽑아낸 결과 : " + personApiResponse);
        return personApiResponse;
    }
}
