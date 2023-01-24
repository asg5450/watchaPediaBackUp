package com.watcha.watchapedia.service;

import com.watcha.watchapedia.model.entity.AdminUser;
import com.watcha.watchapedia.model.network.Header;
import com.watcha.watchapedia.model.network.request.AdminApiRequest;
import com.watcha.watchapedia.model.network.response.AdminApiResponse;
import com.watcha.watchapedia.model.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminApiLogicService extends BaseService<AdminApiRequest, AdminApiResponse, AdminUser> {

    private final AdminRepository adminRepository;

    private AdminApiResponse response(AdminUser admin){
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

        AdminUser users = AdminUser.builder()
                .adminId(adminUserApiRequest.getAdminId())
                .adminPw(adminUserApiRequest.getAdminPw())
                .adminNumber(adminUserApiRequest.getAdminNumber())
                .adminType(adminUserApiRequest.getAdminType())
                .adminName(adminUserApiRequest.getAdminName())
                .build();
        AdminUser newUsers = adminRepository.save(users);
        return Header.OK(response(newUsers));
    }

    @Override
    public Header<AdminApiResponse> read(Long id) {
        return null;
    }

    public Header<AdminApiResponse> read(String userid, String userpw) {
        return adminRepository.findByAdminIdAndAdminPw(userid, userpw).map(
                        users -> response(users)).map(Header::OK)
                .orElseGet(()->Header.ERROR("아이디 또는 비밀번호가 틀렸음!"));
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
