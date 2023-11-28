package com.dana.danapay.money.model.dao;

import com.dana.danapay.money.model.dto.MoneyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MoneyMapper {

    /* MONEY-1. 예치금 조회 */
//    @Select("SELECT * FROM MONEY WHERE CODE = #{code}")
    List<MoneyDTO> searchMoneyByCode(int code);



}
