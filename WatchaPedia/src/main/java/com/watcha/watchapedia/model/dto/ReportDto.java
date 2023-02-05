package com.watcha.watchapedia.model.dto;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.watcha.watchapedia.model.entity.*;

import java.time.LocalDateTime;
import java.util.List;

public record ReportDto(
        Long reportIdx,
        User user,
        String reportCommType,
        Long reportCommIdx,
        String reportText,
        Long reportSpoiler,
        Long reportInappropriate,
        String reportProcessing,
        String reportReporter,
        LocalDateTime reportRegDate

) {
    public static ReportDto of(Long reportIdx, User user, String reportCommType, Long reportCommIdx, String reportText, Long reportSpoiler,
                               Long reportInappropriate, String reportProcessing, String reportReporter, LocalDateTime reportRegDate){
        return new ReportDto(reportIdx, user, reportCommType, reportCommIdx, reportText, reportSpoiler, reportInappropriate, reportProcessing, reportReporter, reportRegDate);
    }

    public static ReportDto from(Report report){
        String processingStatus = "대기중";
        if(report.getReportProcessing() != null){
            processingStatus = report.getReportProcessing();
        }

        return new ReportDto(
                report.getReportIdx(),
                report.getUser(),
                report.getReportCommType(),
                report.getReportCommIdx(),
                report.getReportText(),
                report.getReportSpoiler(),
                report.getReportInappropriate(),
                processingStatus,
                report.getReportReporter(),
                report.getReportRegDate()
        );
    }




}
