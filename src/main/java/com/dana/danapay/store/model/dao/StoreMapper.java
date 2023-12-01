package com.dana.danapay.store.model.dao;


import com.dana.danapay.auth.model.dto.LoginStore;
import com.dana.danapay.common.Criteria;
import com.dana.danapay.store.model.dto.StoreDTO;
import com.dana.danapay.store.param.StoreListReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {
    int insertNewStore(StoreDTO storeRequest);

    Object selectStoreByCode(int registCode);

    List<Object> selectTotalCount(StoreListReq criteria);

    List<StoreDTO> selectStoreList(@Param("criteria") Criteria criteria, @Param("location") StoreListReq location);

    <T> String getPassword(T storeRequest);
//    String getPassword(StorePasswordOnly storeRequest);

    <T> int updateStore(T storeRequest);

    int deleteStore(StoreDTO storeRequest);

    LoginStore findByStoreId(String id);

//    void updatePasswordStore(StoreReq storeRequest);
}
