package com.dana.danapay.orders.model.dto;

import com.dana.danapay.menu.menuDTO;
import com.dana.danapay.orderMenu.model.dto.OrderMenuDTO;
import com.dana.danapay.store.StoreDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponseDTO extends OrdersDTO{

    private StoreDTO store;


}
