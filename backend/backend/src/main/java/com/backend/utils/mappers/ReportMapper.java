package com.backend.utils.mappers;

import com.backend.dtos.ReportDTO;
import com.backend.entities.Report;

import java.util.List;
import java.util.stream.Collectors;

public class ReportMapper {
    public static List<ReportDTO> mapListEntityToListDTO(List<Report> reports) {
        return reports.stream()
                .map(ReportMapper::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    public static ReportDTO mapEntityToDTO(Report report) {
        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setId(report.getId());
        reportDTO.setReason(report.getReason());
        reportDTO.setContentId(report.getContentId());
        reportDTO.setContentType(report.getContentType().toString());
        reportDTO.setStatus(report.getStatus().toString());
        reportDTO.setCreatedAt(report.getCreatedAt());
        reportDTO.setUpdatedAt(report.getUpdatedAt());

        return reportDTO;
    }

    public static List<ReportDTO> mapListEntityToListDTOFull(List<Report> reports) {
        return reports.stream()
                .map(ReportMapper::mapEntityToDTOFull)
                .collect(Collectors.toList());
    }

    public static ReportDTO mapEntityToDTOFull(Report report) {
        ReportDTO reportDTO = mapEntityToDTO(report);

        if (report.getSender() != null) {
            reportDTO.setSender(UserMapper.mapEntityToDTO(report.getSender()));
        }

        return reportDTO;
    }
}
