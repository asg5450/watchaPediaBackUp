package com.watcha.watchapedia.model.network.response;

import com.watcha.watchapedia.model.dto.ReportDto;
import com.watcha.watchapedia.model.entity.Comment;
import com.watcha.watchapedia.model.entity.Recomment;
import com.watcha.watchapedia.model.entity.Report;
import com.watcha.watchapedia.model.entity.User;
import com.watcha.watchapedia.model.repository.CommentRepository;
import com.watcha.watchapedia.model.repository.RecommentRepository;
import com.watcha.watchapedia.model.repository.ReportRepository;

import java.time.LocalDateTime;
import java.util.List;

public record ReportResponseDto(
        Long reportIdx,
        User user,
        String commType,
        Long reportSpoiler,
        Long reportInappropriate,
        String reportProcessing,
        LocalDateTime reportRegDate

) {
    public static ReportResponseDto of(Long reportIdx,
                                       User user,
                                       String commType,
                                       Long reportSpoiler,
                                       Long reportInappropriate,
                                       String reportProcessing,
                                       LocalDateTime reportRegDate){
        return new ReportResponseDto(reportIdx, user, commType, reportSpoiler, reportInappropriate, reportProcessing, reportRegDate);
    }

    public static ReportResponseDto from(ReportDto report){

        return new ReportResponseDto(
                report.reportIdx(),
                report.user(),
                report.reportCommType(),
                report.reportSpoiler(),
                report.reportInappropriate(),
                report.reportProcessing(),
                report.reportRegDate()
        );
    }

    public static ReportResponseDto from(ReportDto report, String commType){

        Long spoiler = 0L;
        if(report.reportSpoiler() != null){
            spoiler = report.reportSpoiler();
        }

        Long inappropriate = 0L;
        if(report.reportInappropriate() != null){
            inappropriate = report.reportInappropriate();
        }

        return new ReportResponseDto(
                report.reportIdx(),
                report.user(),
                commType,
                spoiler,
                inappropriate,
                report.reportProcessing(),
                report.reportRegDate()
        );
    }




}
