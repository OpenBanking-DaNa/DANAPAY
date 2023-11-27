package com.dana.danapay.store.model.service;

import com.dana.danapay.store.StoreDTO;
import com.dana.danapay.store.model.dao.StoreMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class StoreService {

    private final StoreMapper storeMapper;
    private final PasswordEncoder passwordEncoder;

    public StoreService(StoreMapper storeMapper, PasswordEncoder passwordEncoder) {
        this.storeMapper = storeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Object insertNewStore(StoreDTO storeRequest) {
        log.info("StoreService ============> storeRequest {}", storeRequest);


        storeRequest.setsPassword(passwordEncoder.encode(storeRequest.getsPassword()));

        System.out.println("storeRequest.hashCode() = " + storeRequest.hashCode());

        int result = storeMapper.insertNewStore(storeRequest);

        if( result > 0 ) {

            int registCode = storeRequest.getsCode();

            return storeMapper.selectStoreByCode(registCode);
        }
        else {
            return "스토어 등록 실패";
        }
    }
}
