package com.TungLam.request;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import java.security.PrivateKey;
import java.util.List;

@Data
public class AddCartItemRequest {

    private Long foodId;

    private int quantity;

    private List<String> ingredients;

}
