package com.dana.danapay.store.model.dao;


import com.dana.danapay.store.StoreDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper {
    int insertNewStore(StoreDTO storeRequest);

    Object selectStoreByCode(int registCode);
}
