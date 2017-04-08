package com.wuspace.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SchedulerJob implements ValueObject<SchedulerJob> {

    protected String identify;

    protected String group;

    protected String corn;

    protected String description;

    protected Map<String, Object> jobData = new HashMap<>();

    @Override
    public boolean sameValueAs(final SchedulerJob other) {
        return this.equals(other);
    }

}
