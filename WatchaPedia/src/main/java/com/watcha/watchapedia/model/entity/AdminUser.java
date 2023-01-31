package com.watcha.watchapedia.model.entity;

import com.watcha.watchapedia.model.config.Auditable;
import com.watcha.watchapedia.model.config.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity(name = "tbAdminUser")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdminUser extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminIdx ;
    private String adminName ;
    private String adminId ;
    private String adminPw ;
    private String adminNumber;
    private String adminType ;

    public AdminUser(String adminName, String adminId,
                     String adminPw, String adminNumber,
                     String adminType){

        this.adminName = adminName;
        this.adminId = adminId;
        this.adminPw = adminPw;
        this.adminNumber = adminNumber;
        this.adminNumber = adminType;
    }

    public static AdminUser of(String adminName, String adminId,
                               String adminPw, String adminNumber,
                               String adminType){
        return new AdminUser(adminName, adminId, adminPw, adminNumber, adminType);
    }


    @Override
    public int hashCode() {
        return Objects.hash(adminIdx);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof AdminUser adminUser)) return false;
        return adminIdx != null;
    }

}
