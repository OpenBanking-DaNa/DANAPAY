package com.dana.danapay.orders.model.dao;

import com.dana.danapay.orderMenu.model.dto.OrderMenuDTO;
import com.dana.danapay.orders.model.dto.OrdersDTO;
import com.dana.danapay.orders.model.dto.OrdersResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersMapper {

    /* ORDERS-1. 선택 메뉴 주문 */
    void order(OrdersDTO ordersDTO);

    /* ORDERS-1. 선택 메뉴 주문 - 메뉴*/
    void orderMenu(OrderMenuDTO orderMenuDTO);


    /* ORDERS-2. 주문상태 변경 - 업체조회 */
    int searchSCode(String orderCode);

    /* ORDERS-2. 주문상태 변경 */
    void orderProcess(String orderCode, String updateStatus);

    /* ORDERS-3. 주문내역 조회 */
    List<OrdersResponseDTO> searchOrders(int code);
}
