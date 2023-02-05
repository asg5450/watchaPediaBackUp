package com.watcha.watchapedia.service;

import com.watcha.watchapedia.model.dto.ReportDto;
import com.watcha.watchapedia.model.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportApiLogicService {
    private final ReportRepository reportRepository;

    public List<ReportDto> findAllReport(){
        return reportRepository.findAll().stream().map(ReportDto::from).toList();
    }
}
