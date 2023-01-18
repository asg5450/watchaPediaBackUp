package com.watcha.watchapedia.service;

import com.watcha.watchapedia.model.entity.TbAdminUser;
import com.watcha.watchapedia.model.network.Header;
import com.watcha.watchapedia.model.network.request.AdminApiRequest;
import com.watcha.watchapedia.model.network.response.AdminApiResponse;
import com.watcha.watchapedia.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdminApiLogicService extends BaseService<AdminApiRequest, AdminApiResponse, TbAdminUser> {

    private final AdminRepository adminRepository;

    private AdminApiResponse response(TbAdminUser admin){
        AdminApiResponse adminApiResponse = AdminApiResponse.builder()
                .id(admin.getAdminIdx())
                .adminId(admin.getAdminId())
                .adminName(admin.getAdminName())
                .adminNumber(admin.getAdminNumber())
                .adminPw(admin.getAdminPw())
                .adminType(admin.getAdminType())
                .build();

        System.out.println(adminApiResponse);

        return adminApiResponse;
    }

    public Header<AdminApiResponse> idCheck(Header<AdminApiRequest> request) {
        String inputId = request.getData().getAdminId();
        System.out.println(inputId);
        return adminRepository.findByAdminId(inputId).map(admin -> response(admin)).map(result -> Header.OK(result)).orElseGet(()->Header.ERROR("사용가능"));

        //admin_idx	    admin_name	    admin_id	admin_pw	admin_number	admin_type  	reg_date	            update_date
        //1	            안승균	        asg5450	    asg1592	    2066001	        hradmin	        2023-01-17 21:21:40	    2023-01-17 21:21:40

    }

    public Header<AdminApiResponse> employeeCheck(Header<AdminApiRequest> request) {
        String inputId = request.getData().getAdminNumber();
        System.out.println(inputId);
        return adminRepository.findByAdminNumber(inputId).map(admin -> response(admin)).map(Header::OK).orElseGet(()->Header.ERROR("사용가능"));
    }

    @Override
    public Header<AdminApiResponse> create(Header<AdminApiRequest> request) {
        AdminApiRequest adminUserApiRequest = request.getData();

        TbAdminUser users = TbAdminUser.builder()
                .adminId(adminUserApiRequest.getAdminId())
                .adminPw(adminUserApiRequest.getAdminPw())
                .adminNumber(adminUserApiRequest.getAdminNumber())
                .adminType(adminUserApiRequest.getAdminType())
                .adminName(adminUserApiRequest.getAdminName())
                .build();
        TbAdminUser newUsers = adminRepository.save(users);
        return Header.OK(response(newUsers));
    }

    @Override
    public Header<AdminApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<AdminApiResponse> update(Header<AdminApiRequest> request) {
        return null;
    }

    @Override
    public Header<AdminApiResponse> delete(Long id) {
        return null;
    }
}
