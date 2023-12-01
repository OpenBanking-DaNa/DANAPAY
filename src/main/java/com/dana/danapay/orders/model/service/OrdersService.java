package com.dana.danapay.orders.model.service;

import com.dana.danapay.member.model.dao.MemberMapper;
import com.dana.danapay.money.model.dao.MoneyMapper;
import com.dana.danapay.money.model.dto.MoneyDTO;
import com.dana.danapay.orderMenu.model.dto.OrderMenuDTO;
import com.dana.danapay.orders.model.dao.OrdersMapper;
import com.dana.danapay.orders.model.dto.OrdersDTO;
import com.dana.danapay.orders.model.dto.OrdersResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class OrdersService {

    private final OrdersMapper ordersMapper;
    private final MemberMapper memberMapper;
    private final MoneyMapper moneyMapper;

    public OrdersService(OrdersMapper ordersMapper, MemberMapper memberMapper, MoneyMapper moneyMapper) {
        this.ordersMapper = ordersMapper;
        this.memberMapper = memberMapper;
        this.moneyMapper = moneyMapper;
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

            // 예치금 차감
            MoneyDTO moneyDTO = new MoneyDTO();
            moneyDTO.setCode(ordersDTO.getCode());
            moneyDTO.setMoney(ordersDTO.getTotalPrice());
            moneyDTO.setOption("사용");
            log.info("orderMenuList moneyDTO  {}", moneyDTO);

            moneyMapper.chargeMoney(moneyDTO);
            memberMapper.chargeMoney(moneyDTO.getCode(), moneyDTO.getMoney(), moneyDTO.getOption());

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
            ordersMapper.orderProcess(orderCode, updateStatus);

            log.info("OrdersService - orderProcess---- end");
            return true;
        } catch (Exception e) {
            log.error("에러발생 OrdersService - orderProcess", e);
            throw e;
        }
    }

    /* ORDERS-3. 주문내역 조회 */
    public List<OrdersResponseDTO> searchOrders(int code) {
        log.info("OrdersService - searchOrders---- start");
        try {
            List<OrdersResponseDTO> result = ordersMapper.searchOrders(code);
            log.info("OrdersService - result {}", result);
            log.info("OrdersService - searchOrders---- end");
            return result;
        } catch (Exception e) {
            log.error("에러발생 OrdersService - searchOrders", e);
            throw e;
        }
    }

    /* ORDERS-4. 주문내역 상세조회 */
    public List<OrdersResponseDTO> searchDetailOrders(String orderCode) {
        log.info("OrdersService - searchDetailOrders---- start");
        try {
            List<OrdersResponseDTO> result = ordersMapper.searchDetailOrders(orderCode);
            log.info("OrdersService - result {}", result);
            log.info("OrdersService - searchDetailOrders---- end");
            return result;
        } catch (Exception e) {
            log.error("에러발생 OrdersService - searchDetailOrders", e);
            throw e;
        }
    }

    /* ORDERS-5. 주문상태 조회 */
    public String searchStatusOrders(String orderCode) {
        log.info("OrdersService - searchStatusOrders---- start");
        try {
            String result = ordersMapper.searchStatusOrders(orderCode);
            log.info("OrdersService - result {}", result);
            log.info("OrdersService - searchStatusOrders---- end");
            return result;
        } catch (Exception e) {
            log.error("에러발생 OrdersService - searchStatusOrders", e);
            throw e;
        }
    }
}


