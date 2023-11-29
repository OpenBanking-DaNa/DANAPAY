package com.dana.danapay.store.model.dao;


import com.dana.danapay.common.Criteria;
import com.dana.danapay.store.StoreDTO;
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
}
