package com.watcha.watchapedia.service;


import com.watcha.watchapedia.model.entity.Notice;
import com.watcha.watchapedia.model.network.Header;
import com.watcha.watchapedia.model.network.request.NoticeApiRequest;
import com.watcha.watchapedia.model.network.response.NoticeApiResponse;
import com.watcha.watchapedia.model.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeApiLogicService extends BaseService<NoticeApiRequest, NoticeApiResponse, Notice> {

    private final NoticeRepository tbNoticeRepository;

    private NoticeApiResponse response(Notice tbNotice){
        NoticeApiResponse noticeApiResponse = NoticeApiResponse.builder()
                .id(tbNotice.getId())
                .ntcTitle(tbNotice.getNtcTitle())
                .ntcText(tbNotice.getNtcText())
                .ntcBtnText(tbNotice.getNtcBtnText())
                .ntcBtnColor(tbNotice.getNtcBtnColor())
                .ntcImagepath(tbNotice.getNtcImagepath())
                .build();
        return noticeApiResponse;
    }

    @Override
    public Header<NoticeApiResponse> create(Header<NoticeApiRequest> request) {
        System.out.println("logicService create() 메소드 실행됨!");

        NoticeApiRequest noticeApiRequest = request.getData();

        System.out.println(noticeApiRequest);

        Notice newNotice = Notice.builder()
                .ntcTitle(noticeApiRequest.getNtcTitle())
                .ntcText(noticeApiRequest.getNtcText())
                .ntcImagepath(noticeApiRequest.getNtcImagepath())
                .ntcBtnColor(noticeApiRequest.getNtcBtnColor())
                .ntcBtnText(noticeApiRequest.getNtcBtnText())
                .ntcStatus("미등록")
                .build();

        System.out.println(newNotice);
        Notice newTbNotice = tbNoticeRepository.save(newNotice);

        return Header.OK(response(newTbNotice));

    }

    @Override
    public Header<NoticeApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<NoticeApiResponse> update(Header<NoticeApiRequest> request) {
        return null;
    }

    @Override
    public Header<NoticeApiResponse> delete(Long id) {
        return null;
    }
}
