package ca.shopify.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aark on 2015-10-03.
 */
public class Image {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("position")
    @Expose
    public String position;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("product_id")
    @Expose
    public String productId;
    @SerializedName("variant_ids")
    @Expose
    public List<String> variantIds = new ArrayList<String>();
    @SerializedName("src")
    @Expose
    public String src;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<String> getVariantIds() {
        return variantIds;
    }

    public void setVariantIds(List<String> variantIds) {
        this.variantIds = variantIds;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
