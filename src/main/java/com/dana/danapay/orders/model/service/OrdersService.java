package com.dana.danapay.orders.model.service;

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

    public OrdersService(OrdersMapper ordersMapper) {
        this.ordersMapper = ordersMapper;
    }

    /* ORDERS-1. 선택 메뉴 주문 */
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


    /* ORDERS-2. 주문상태 변경 */
    @Transactional
    public boolean orderProcess(String orderCode, String updateStatus) {
        log.info("OrdersService - orderProcess---- start");
        try {
            log.info("orderCode {}", orderCode);
            log.info("updateStatus {}", updateStatus);


            ordersMapper.orderProcess(orderCode, updateStatus);

            log.info("OrdersService - orderProcess---- end");
            return true;
        } catch (Exception e) {
            log.error("에러발생 OrdersService - orderProcess", e);
            throw e;
        }
    }

}


