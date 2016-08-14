
package ru.mydelivery.Model.Main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("jobs_id")
    @Expose
    private String jobsId;
    @SerializedName("fio")
    @Expose
    private String fio;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("status_text")
    @Expose
    private String statusText;
    @SerializedName("date")
    @Expose
    private String date;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJobsId() {
        return jobsId;
    }

    public void setJobsId(String jobsId) {
        this.jobsId = jobsId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
