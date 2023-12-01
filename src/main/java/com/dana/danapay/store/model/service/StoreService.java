package com.dana.danapay.store.model.service;

import com.dana.danapay.common.Criteria;
import com.dana.danapay.common.Pagination;
import com.dana.danapay.exception.PasswordException;
import com.dana.danapay.store.model.dto.StoreAccount;
import com.dana.danapay.store.model.dto.StoreDTO;
import com.dana.danapay.store.model.dao.StoreMapper;
import com.dana.danapay.store.param.StoreListReq;
import com.dana.danapay.store.param.StoreListRes;
import com.dana.danapay.store.param.StorePasswordOnly;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StoreService {

    private final StoreMapper storeMapper;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper mm;


    public StoreService(StoreMapper storeMapper, PasswordEncoder passwordEncoder, ModelMapper mm) {
        this.storeMapper = storeMapper;
        this.passwordEncoder = passwordEncoder;
        this.mm = mm;
    }

    // 스토어 신규 등록
    @Transactional
    public Object addStore(StoreDTO storeRequest) {
        log.info("StoreService ============> storeRequest {}", storeRequest);


        storeRequest.setSPassword(passwordEncoder.encode(storeRequest.getSPassword()));

        System.out.println("storeRequest.hashCode() = " + storeRequest.hashCode());

        int result = storeMapper.insertNewStore(storeRequest);

        if( result > 0 ) {

            int registCode = storeRequest.getSCode();

            return storeMapper.selectStoreByCode(registCode);
        }
        else {
            return "S401 : 스토어 등록 실패";
        }
    }

    // 스토어 목록 조회 (2km 이내만)
    public Object selectStoreList(StoreListReq storeReq) {

        int count = storeMapper.selectTotalCount(storeReq).size();
        log.info("StoreService selectTotalCount============> count {}", count);
        if(count > 0) {

            // 보여줄 게시물 수, 버튼수 설정
            int limit = 2;
            int button = 5;

            Criteria criteria = null;

            // 검색조건 있는 경우
            if(storeReq.getSearchCondition() != null && !storeReq.getSearchCondition().equals("")){
                criteria = Pagination.getCriteria(storeReq.getCurrentPageNo(), count, limit, button, storeReq.getSearchCondition(), storeReq.getSearchValue());
            // 검색조건 없는 경우
            } else {
                criteria = Pagination.getCriteria(storeReq.getCurrentPageNo(), count, limit, button);
            }
            log.info("StoreService selectStoreList============> criteria {}", criteria);

            // 조회
            List<StoreDTO> storeDTOS = storeMapper.selectStoreList(criteria, storeReq);
            List<StoreListRes> storeList = storeDTOS.stream().map(store -> mm.map(store, StoreListRes.class)).collect(Collectors.toList());

            storeList.forEach(store -> store.setDistance((int)Math.ceil(distance(storeReq.getsY(), storeReq.getsX(), store.getsY(), store.getsX()))));

            log.info("StoreService selectStoreList============> storeList {}", storeList);

            return storeList;
        } else {
            return "반경 2km 이내 스토어 없음";
        }
    }

    // 스토어 정보 수정
    @Transactional
    public <T extends StoreAccount> Object replaceStore(T storeRequest) {
        log.info("StoreService updateStore============> storeRequest {}", storeRequest);

        try {
            // 비밀번호 체크 불일치
            if (!passwordCheck(storeRequest)) {
                return "비밀번호가 일치하지 않습니다.";
            }
            // 일치
            storeMapper.updateStore(storeRequest);
            return "스토어 수정 성공";
        } catch (Exception e) {
            log.error("Error updating store. Store ID: {}", storeRequest.sId(), e);
            return "스토어 수정 중 오류가 발생했습니다.";
        }
    }

    // 스토어 삭제
    @Transactional
    public Object removeStore(StoreDTO storeRequest) {
        log.info("StoreService deleteStore============> storeRequest {}", storeRequest);

        try {
            // 비밀번호 체크 불일치
            if (!passwordCheck(storeRequest)) {
                return "비밀번호가 일치하지 않습니다.";
            }
            // 일치
            storeMapper.deleteStore(storeRequest);
            return "스토어 삭제 성공";

        } catch (Exception e) {
            log.error("Error deleting store. Store ID: {}", storeRequest.getSId(), e);
            return "스토어 삭제 중 오류가 발생했습니다.";
        }
    }


    // 비밀번호 체크
    private <T extends StoreAccount> boolean passwordCheck(T storeRequest){
        try {
            String encodePassword = storeMapper.getPassword(storeRequest);

            if (encodePassword != null && passwordEncoder.matches(storeRequest.sPassword(), encodePassword)) {
                // 비밀번호 일치
                return true;
            } else {
                // 비밀번호 불일치
                log.warn("비밀번호가 틀립니다. Store ID: " + storeRequest.sId());
                return false;
            }
        } catch (Exception e) {
            // Log the exception and throw a custom exception
            log.error("비밀번호 확인 중 오류가 발생했습니다. Store ID: " + storeRequest.sId(), e);
            throw new PasswordException("비밀번호 확인 중 오류가 발생했습니다.");
        }
    }
    // 좌표간 거리 계산기
    private static double distance(double y1, double x1, double y2, double x2){

        // 지구 반경 (단위: m)
        final double R = 6371000.0;

        // 라디안으로 변환
        double radY1 = Math.toRadians(y1);
        double radX1 = Math.toRadians(x1);
        double radY2 = Math.toRadians(y2);
        double radX2 = Math.toRadians(x2);

        // 위도와 경도의 차이
        double dY = radY2 - radY1;
        double dX = radX2 - radX1;

        // Haversine 공식 계산
        double a = Math.pow(Math.sin(dY / 2), 2) + Math.cos(radY1) * Math.cos(radY2) * Math.pow(Math.sin(dX / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // 거리 계산
        return R * c;

    }


}
