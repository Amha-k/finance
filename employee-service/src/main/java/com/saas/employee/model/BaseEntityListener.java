package com.saas.employee.model;

import com.saas.employee.dto.eventDto.ResourceEvent;
import com.saas.employee.dto.request.EmployeeHistoryRequest;
import com.saas.employee.utility.EmployeeHistoryContext;
import com.saas.employee.utility.ResourceEventContext;
import com.saas.employee.utility.SecurityUtil;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseEntityListener {

    private final SecurityUtil securityUtil;

    @PrePersist
    public void setCreatedBy(Base base) {
        ResourceEvent resourceEvent = ResourceEventContext.get();
        if(resourceEvent != null && resourceEvent.getCreatedBy() != null) {
            base.setCreatedBy(resourceEvent.getCreatedBy());
        } else {
            String name = securityUtil.getName();
            base.setCreatedBy(name != null ? name : "unknown");
        }
    }

    @PreUpdate
    public void setUpdatedBy(Base base) {
        EmployeeHistoryRequest event = EmployeeHistoryContext.get();
        if (event != null && event.getCreatedBy() != null) {
            base.setUpdatedBy(event.getCreatedBy());
        } else {
            String name = securityUtil.getName();
            base.setUpdatedBy(name != null ? name : "unknown");
        }
    }
}
