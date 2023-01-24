package com.watcha.watchapedia.model.entity;

import com.watcha.watchapedia.model.config.Auditable;
import com.watcha.watchapedia.model.config.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


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

}
