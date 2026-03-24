package com.saas.employee.utility;

import com.saas.employee.dto.eventDto.ResourceEvent;

public class ResourceEventContext {

    private static final ThreadLocal<ResourceEvent> context = new ThreadLocal<>();

    public static void set(ResourceEvent event) {
        context.set(event);
    }

    public static ResourceEvent get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}