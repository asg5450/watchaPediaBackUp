package com.watcha.watchapedia.service;

import com.watcha.watchapedia.model.entity.TbAdvertise;
import com.watcha.watchapedia.model.network.Header;
import com.watcha.watchapedia.model.network.request.AdvertiseApiRequest;
import com.watcha.watchapedia.model.network.response.AdvertiseApiResponse;
import com.watcha.watchapedia.repository.AdvertiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertiseApiLogicService extends BaseService<AdvertiseApiRequest, AdvertiseApiResponse, TbAdvertise> {

    private final AdvertiseRepository advertiseRepository;

    private AdvertiseApiResponse response(TbAdvertise tb_advertise) {
        AdvertiseApiResponse advertiseApiResponse = AdvertiseApiResponse.builder()
                .id(tb_advertise.getId())
                .adTitle(tb_advertise.getAdTitle())
                .adContent(tb_advertise.getAdContent())
                .adStatus(tb_advertise.getAdStatus())
                .build();
        return advertiseApiResponse;
    }

    @Override
    public Header<AdvertiseApiResponse> create(Header<AdvertiseApiRequest> request) {
        AdvertiseApiRequest advertiseApiRequest = request.getData();

        System.out.println(advertiseApiRequest);

        TbAdvertise tbAdvertise = TbAdvertise.builder()
                .adTitle(advertiseApiRequest.getAd_title())
                .adContent(advertiseApiRequest.getAd_content())
                .adStatus("미등록")
                .build();

        TbAdvertise newTbAdvertise = advertiseRepository.save(tbAdvertise);

        return Header.OK(response(newTbAdvertise));
    }

    @Override
    public Header<AdvertiseApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<AdvertiseApiResponse> update(Header<AdvertiseApiRequest> request) {
        return null;
    }

    @Override
    public Header<AdvertiseApiResponse> delete(Long id) {
        return null;
    }
}
