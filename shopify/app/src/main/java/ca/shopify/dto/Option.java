package ca.shopify.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aark on 2015-10-03.
 */
public class Option {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("position")
    @Expose
    public String position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
