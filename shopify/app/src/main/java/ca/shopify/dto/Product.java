package ca.shopify.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aark on 2015-10-03.
 */
public class Product implements Comparable<Product> {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("handle")
    @Expose
    public String handle;
    @SerializedName("body_html")
    @Expose
    public String bodyHtml;
    @SerializedName("published_at")
    @Expose
    public String publishedAt;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("vendor")
    @Expose
    public String vendor;
    @SerializedName("product_type")
    @Expose
    public String productType;
    @SerializedName("tags")
    @Expose
    public List<String> tags = new ArrayList<String>();
    @SerializedName("variants")
    @Expose
    public List<Variant> variants = new ArrayList<Variant>();
    @SerializedName("images")
    @Expose
    public List<Image> images = new ArrayList<Image>();
    @SerializedName("options")
    @Expose
    public List<Option> options = new ArrayList<Option>();
    public Product() {
    }
    public Product(String id, String title, String handle, String bodyHtml, String publishedAt, String createdAt, String updatedAt, String vendor, String productType, List<String> tags, List<Variant> variants, List<Image> images, List<Option> options) {
        this.id = id;
        this.title = title;
        this.handle = handle;
        this.bodyHtml = bodyHtml;
        this.publishedAt = publishedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.vendor = vendor;
        this.productType = productType;
        this.tags = tags;
        this.variants = variants;
        this.images = images;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getHandle() {
        return handle;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getVendor() {
        return vendor;
    }

    public String getProductType() {
        return productType;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public int compareTo(Product another) {
        return getProductType().toLowerCase().compareTo(another.getProductType().toLowerCase());

    }
}
