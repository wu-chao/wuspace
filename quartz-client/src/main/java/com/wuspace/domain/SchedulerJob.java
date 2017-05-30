package com.wuspace.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SchedulerJob implements ValueObject<SchedulerJob> {

    protected String identify;

    protected String group;

    protected String cron;

    protected String description;

    protected Map<String, String> jobDataMap = new HashMap<>();

    @Override
    public boolean sameValueAs(final SchedulerJob other) {
        return this.equals(other);
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getJobDataMap() {
        return jobDataMap;
    }

    public void setJobDataMap(Map<String, String> jobDataMap) {
        this.jobDataMap = jobDataMap;
    }
}
