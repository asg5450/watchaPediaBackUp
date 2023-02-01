package com.watcha.watchapedia.service;

import com.watcha.watchapedia.model.entity.Character;
import com.watcha.watchapedia.model.network.Header;
import com.watcha.watchapedia.model.network.request.CharacterApiRequest;
import com.watcha.watchapedia.model.network.response.CharacterApiResponse;
import com.watcha.watchapedia.model.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterApiLogicService extends BaseService<CharacterApiRequest, CharacterApiResponse, Character>{
    private final CharacterRepository characterRepository;

    private CharacterApiResponse response(Character character){
        CharacterApiResponse characterApiResponse = CharacterApiResponse.builder()
                .perIdx(character.getPerIdx())
                .perName(character.getPerName())
                .perPhoto(character.getPerPhoto())
                .perRole(character.getPerRole())
                .perBiography(character.getPerBiography())
                .perBook(character.getPerBook())
                .perMov(character.getPerMov())
                .perTv(character.getPerTv())
                .perWebtoon(character.getPerWebtoon())
                .build();
        return characterApiResponse;
    }

    @Override
    public Header<CharacterApiResponse> create(Header<CharacterApiRequest> request) {
        CharacterApiRequest characterApiRequest = request.getData();
        Character character = Character.builder()
                .perName(characterApiRequest.getPerName())
                .perPhoto(characterApiRequest.getPerPhoto())
                .perBiography(characterApiRequest.getPerBiography())
                .build();
        Character newCharacter = baseRepository.save(character);
        return Header.OK(response(newCharacter));
    }



    @Override
    public Header<CharacterApiResponse> read(Long id) {
        return baseRepository.findById(id).map(character -> response(character))
                .map(Header::OK).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CharacterApiResponse> update(Header<CharacterApiRequest> request) {
        CharacterApiRequest characterApiRequest = request.getData();
        Optional<Character> characters = characterRepository.findByPerIdx(characterApiRequest.getPerIdx());
        System.out.println(characters);
        return characters.map(
                        character -> {
                            character.setPerBiography(characterApiRequest.getPerBiography());
                            character.setPerName(characterApiRequest.getPerName());
                            character.setPerPhoto(characterApiRequest.getPerPhoto());
                            return character;
                        }).map(character -> characterRepository.save(character))
                .map(character -> response(character))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header delete(Long perIdx) {
        Optional<Character> character = baseRepository.findById(perIdx);
        return character.map(character1 -> {
            baseRepository.delete(character1);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    public Header<List<CharacterApiResponse>> search(){
        List<Character> characters = baseRepository.findAll();
        List<CharacterApiResponse> characterApiResponse = characters.stream().map(
                character -> response(character)).collect(Collectors.toList());


        return Header.OK(characterApiResponse);
    }


    public List<Character> characterList(){
        System.out.println("잘들어옴");
        return characterRepository.findAll();
    }
}


