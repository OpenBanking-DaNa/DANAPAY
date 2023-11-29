package com.dana.danapay.store.model.service;

import com.dana.danapay.common.Criteria;
import com.dana.danapay.common.Pagination;
import com.dana.danapay.store.StoreDTO;
import com.dana.danapay.store.model.dao.StoreMapper;
import com.dana.danapay.store.param.StoreListReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            return "S401 : 스토어 등록 실패";
        }
    }

    public Object selectStoreList(StoreListReq storeReq) {

        int count = storeMapper.selectTotalCount(storeReq).size();
        log.info("StoreService selectTotalCount============> count {}", count);
        if(count > 0) {

            // 보여줄 게시물 수, 버튼수 설정
            int limit = 2;
            int button = 5;

            Criteria criteria = null;

            // 검색조건 있는 경우
            if(storeReq.getSearchCondition() != null && storeReq.getSearchCondition().equals("")){
                criteria = Pagination.getCriteria(storeReq.getCurrentPageNo(), count, limit, button, storeReq.getSearchCondition(), storeReq.getSearchValue());
            // 검색조건 없는 경우
            } else {
                criteria = Pagination.getCriteria(storeReq.getCurrentPageNo(), count, limit, button);
            }
            log.info("StoreService selectStoreList============> criteria {}", criteria);

            // 조회
            List<StoreDTO> storeList = storeMapper.selectStoreList(criteria, storeReq);
            log.info("StoreService selectStoreList============> storeList {}", storeList);

             return storeList;
        } else {
            return "반경 2km 이내 스토어 없음";
        }
    }
}
