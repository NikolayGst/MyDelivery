
package ru.mydelivery.network.Model.Detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class JobForUser {

    @SerializedName("user")
    @Expose
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
