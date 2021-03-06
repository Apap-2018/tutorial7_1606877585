package com.apap.tutorial07.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PilotDetail {
    private String status;

    @JsonProperty("valid-until")
    private Date validUntil;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
