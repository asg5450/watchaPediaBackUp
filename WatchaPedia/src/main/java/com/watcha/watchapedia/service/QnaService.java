package com.watcha.watchapedia.service;



import com.watcha.watchapedia.model.dto.QnaDto;
import com.watcha.watchapedia.model.entity.Qna;
import com.watcha.watchapedia.model.network.request.QnaRequest;
import com.watcha.watchapedia.model.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class QnaService {
    @Autowired
    final QnaRepository qnaRepository;
    @Transactional(readOnly = true) //데이터를 불러오기만 할 때(수정X)
    public List<Qna> searchQnas(){
        return qnaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public QnaDto getQna(Long qnaIdx){
        return qnaRepository.findById(qnaIdx)
                .map(QnaDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - qnaidx: " + qnaIdx));
    }

    public void saveQna(QnaDto dto){
        qnaRepository.save(dto.toEntity());

    }


    public void updateQna(Long qnaIdx, String qnaText){
        try{
            Qna qna = qnaRepository.getReferenceById(qnaIdx);
            qna.setQnaDtext(qnaText);
        }catch(EntityNotFoundException e){
            log.warn("게시글 업데이트 실패. 게시글을 찾을 수 없음 - dto:");
        }
    }

}
