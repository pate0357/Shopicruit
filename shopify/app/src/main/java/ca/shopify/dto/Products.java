package ca.shopify.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aark on 2015-10-03.
 */
public class Products {
    @SerializedName("products")
    @Expose
    public List<Product> products = new ArrayList<Product>();

    public List<Product> getProducts() {
        return products;
    }
}
