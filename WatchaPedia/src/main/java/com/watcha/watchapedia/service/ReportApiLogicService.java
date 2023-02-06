package com.watcha.watchapedia.service;

import com.watcha.watchapedia.model.dto.ReportDto;
import com.watcha.watchapedia.model.entity.Report;
import com.watcha.watchapedia.model.network.Header;
import com.watcha.watchapedia.model.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportApiLogicService {
    private final ReportRepository reportRepository;

    public List<ReportDto> findAllReport(){
        return reportRepository.findAll().stream().map(ReportDto::from).toList();
    }

    public Header updateReport(Long reportIdx, String updateStatus, String processAdmin, String updateDate){
        try{
            Report report = reportRepository.getReferenceById(reportIdx);
            System.out.println(report);
            report.setReportProcessing(updateStatus + "," + updateDate + "," + processAdmin);
            System.out.println(report);
            reportRepository.save(report);
            return Header.OK(report);
        }catch(EntityNotFoundException e){
            System.out.println("해당하는 report가 없어요;;");
            return Header.ERROR();
        }

    }
}
