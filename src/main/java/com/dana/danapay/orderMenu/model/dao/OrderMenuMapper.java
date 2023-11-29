package com.dana.danapay.orderMenu.model.dao;

import com.dana.danapay.orderMenu.model.dto.OrderMenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMenuMapper {

    // ORDERS-1. 주문 하기
    void orderMenu(List<OrderMenuDTO> orderMenuList);
}
