package com.example.datafromserverusespinner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceData {
    @SerializedName("services_id")
    @Expose
    private String servicesId;
    @SerializedName("services_name")
    @Expose
    private String servicesName;
    @SerializedName("services_status")
    @Expose
    private String servicesStatus;

    public String getServicesId() {
        return servicesId;
    }

    public void setServicesId(String servicesId) {
        this.servicesId = servicesId;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public String getServicesStatus() {
        return servicesStatus;
    }

    public void setServicesStatus(String servicesStatus) {
        this.servicesStatus = servicesStatus;
    }

}



