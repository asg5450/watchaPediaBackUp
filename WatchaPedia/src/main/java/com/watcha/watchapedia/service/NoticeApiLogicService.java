package com.watcha.watchapedia.service;

import com.watcha.watchapedia.model.entity.TbNotice;
import com.watcha.watchapedia.model.network.Header;
import com.watcha.watchapedia.model.network.request.NoticeApiRequest;
import com.watcha.watchapedia.model.network.response.NoticeApiResponse;
import com.watcha.watchapedia.repository.TbNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoticeApiLogicService extends BaseService<NoticeApiRequest, NoticeApiResponse, TbNotice> {

    private final TbNoticeRepository tbNoticeRepository;

    private NoticeApiResponse response(TbNotice tbNotice){
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

        TbNotice newNotice = TbNotice.builder()
                .ntcTitle(noticeApiRequest.getNtcTitle())
                .ntcText(noticeApiRequest.getNtcText())
                .ntcImagepath(noticeApiRequest.getNtcImagepath())
                .ntcBtnColor(noticeApiRequest.getNtcBtnColor())
                .ntcBtnText(noticeApiRequest.getNtcBtnText())
                .ntcStatus("미등록")
                .build();

        System.out.println(newNotice);
        TbNotice newTbNotice = tbNoticeRepository.save(newNotice);

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
