package com.dana.danapay.orders.model.service;

import com.dana.danapay.orderMenu.model.dao.OrderMenuMapper;
import com.dana.danapay.orderMenu.model.dto.OrderMenuDTO;
import com.dana.danapay.orders.model.dao.OrdersMapper;
import com.dana.danapay.orders.model.dto.OrdersDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class OrdersService {

    private final OrdersMapper ordersMapper;
    private final OrderMenuMapper orderMenuMapper;

    public OrdersService(OrdersMapper ordersMapper, OrderMenuMapper orderMenuMapper) {
        this.ordersMapper = ordersMapper;
        this.orderMenuMapper = orderMenuMapper;
    }

    /* ORDERS-1. 주문 하기 */
    @Transactional
    public boolean order(OrdersDTO ordersDTO) {

        log.info("OrdersService - order---- start");
        try {
            log.info("ordersDTO {}", ordersDTO);

            ordersMapper.order(ordersDTO);
            log.info("ordersDTO.getOrderCode() {}", ordersDTO.getOrderCode());

            String orderCode = ordersDTO.getOrderCode();
            log.info("orderCode {}", orderCode);

            List<OrderMenuDTO> orderMenuList = ordersDTO.getOrderMenuList();
            log.info("orderMenuList {}", orderMenuList);

            for (OrderMenuDTO orderMenuDTO : orderMenuList){
                orderMenuDTO.setOrderCode(ordersDTO.getOrderCode());
                ordersMapper.orderMenu(orderMenuDTO);
            }
            log.info("orderMenuList 222  {}", orderMenuList);

            log.info("OrdersService - order---- end");
            return true;
        } catch (Exception e) {
            log.error("에러발생 OrdersService - order", e);
            throw e;
        }


    }
}
