package com.saas.employee.utility;

import com.saas.employee.dto.request.EmployeeHistoryRequest;

public class EmployeeHistoryContext {

    private static final ThreadLocal<EmployeeHistoryRequest> context = new ThreadLocal<>();

    public static void set(EmployeeHistoryRequest request) {
        context.set(request);
    }

    public static EmployeeHistoryRequest get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}