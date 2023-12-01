package com.dana.danapay.auth.model.dao;

import com.dana.danapay.auth.model.dto.LoginUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {


    LoginUser findByStoreId(String id);
    LoginUser findByMemberId(String id);

    String getStorePassword(String id);
    String getMemberPassword(String id);
}

