package com.dana.danapay.orders.model.dto;


import com.dana.danapay.store.model.dto.StoreDTO;
import lombok.*;



@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponseDTO extends OrdersDTO{

    private StoreDTO store;


}
