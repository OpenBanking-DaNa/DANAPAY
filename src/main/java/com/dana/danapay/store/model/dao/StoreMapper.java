package com.dana.danapay.store.model.dao;


import com.dana.danapay.common.Criteria;
import com.dana.danapay.store.model.dto.StoreDTO;
import com.dana.danapay.store.param.StoreListReq;
import com.dana.danapay.store.param.StorePasswordOnly;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {
    int insertNewStore(StoreDTO storeRequest);

    Object selectStoreByCode(int registCode);

    List<Object> selectTotalCount(StoreListReq criteria);

    List<StoreDTO> selectStoreList(@Param("criteria") Criteria criteria, @Param("location") StoreListReq location);


    int deleteStore(StoreDTO storeRequest);


    void wholeUpdateStore(StoreDTO storeRequest);

    void anyUpdateStore(StoreDTO storeRequest);

    boolean isStoreOwner(String code, String id);
}
