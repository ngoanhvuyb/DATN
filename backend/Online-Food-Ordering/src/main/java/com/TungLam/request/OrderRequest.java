package com.TungLam.request;

import com.TungLam.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address deliveryAddress;

}
