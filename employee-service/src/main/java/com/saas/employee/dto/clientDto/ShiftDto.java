package com.saas.employee.dto.clientDto;

import com.saas.employee.dto.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftDto extends BaseResponse {

    private String name;
    private String startTime;
    private String endTime;
}
