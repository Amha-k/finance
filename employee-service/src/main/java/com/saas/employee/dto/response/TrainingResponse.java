package com.saas.employee.dto.response;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingResponse extends BaseResponse {
    private String trainingTitle;
    private String institution;
    private String sponsoredBy;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID employeeId;
    private String fileName;
    private String fileType;
    private byte[] fileBytes;
}
