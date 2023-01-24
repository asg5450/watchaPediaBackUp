package com.watcha.watchapedia.controller.api;

import com.watcha.watchapedia.controller.CrudController;
import com.watcha.watchapedia.model.entity.Notice;
import com.watcha.watchapedia.model.network.Header;
import com.watcha.watchapedia.model.network.request.NoticeApiRequest;
import com.watcha.watchapedia.model.network.response.NoticeApiResponse;
import com.watcha.watchapedia.service.NoticeApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/notice")    // http://localhost:9090/api/notice
@RequiredArgsConstructor
public class NoticeApiController extends CrudController<NoticeApiRequest, NoticeApiResponse, Notice> {
    private final NoticeApiLogicService noticeApiLogicService;

    @Override
    @PostMapping("")    // http://localhost:9090/api/notice
    public Header<NoticeApiResponse> create(@RequestBody Header<NoticeApiRequest> request) {
        System.out.println("create메소드로 잘 들어옴!");
        return noticeApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")    // http://localhost:9090/api/notice/{id} (get)
    public Header<NoticeApiResponse> read(Long id) {
        return noticeApiLogicService.read(id);
    }

    @Override
    @PutMapping("")    // http://localhost:9090/api/notice (put)
    public Header<NoticeApiResponse> update(Header<NoticeApiRequest> request) {
        return noticeApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")    // http://localhost:9090/api/notice/{id} (delete)
    public Header<NoticeApiResponse> delete(Long id) {
        return noticeApiLogicService.delete(id);
    }
}
