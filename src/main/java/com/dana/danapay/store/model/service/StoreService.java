package com.dana.danapay.store.model.service;

import com.dana.danapay.common.Criteria;
import com.dana.danapay.common.Pagination;
import com.dana.danapay.store.StoreDTO;
import com.dana.danapay.store.model.dao.StoreMapper;
import com.dana.danapay.store.param.StoreListReq;
import com.dana.danapay.store.param.StoreListRes;
import com.dana.danapay.store.param.StoreReq;
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
    public Object updateStore(StoreDTO storeRequest) {
        log.info("StoreService updateStore============> storeRequest {}", storeRequest);

        // 비밀번호 조회해온다.
        String encodePassword = storeMapper.getPassword(storeRequest);

        if(encodePassword != null){
            // 비밀번호 체킹
            if(passwordEncoder.matches(storeRequest.getsPassword(), encodePassword)){
                // 업데이트 진행
                storeMapper.updateStore(storeRequest);
                return "스토어 정보 수정 성공";

            } else {
                return "비밀번호가 다릅니다.";
            }
        } else {
            return "스토어 정보를 찾지 못했습니다.";
        }
    }

    // 스토어 삭제
    @Transactional
    public Object deleteStore(StoreDTO storeRequest) {
        log.info("StoreService deleteStore============> storeRequest {}", storeRequest);

        // 비밀번호 조회해온다.
        String encodePassword = storeMapper.getPassword(storeRequest);

        if(encodePassword != null){
            // 비밀번호 체킹
            if(passwordEncoder.matches(storeRequest.getsPassword(), encodePassword)){
                // 삭제 진행
                storeMapper.deleteStore(storeRequest);
                return "스토어 삭제 성공";

            } else {
                return "비밀번호가 다릅니다.";
            }
        } else {
            return "스토어 정보를 찾지 못했습니다.";
        }
    }

    // 스토어 비밀번호 수정
    public Object updatePasswordStore(StoreReq storeRequest) {

        log.info("StoreService updatePasswordStore ============> storeRequest {}", storeRequest);

        // 비밀번호 조회해온다.
        String encodePassword = storeMapper.getPassword(storeRequest);

        if(encodePassword != null && !storeRequest.getNewPassword().equals("")){
            // 비밀번호 체킹
            if(passwordEncoder.matches(storeRequest.getsPassword(), encodePassword)){
                // 업데이트 진행
                storeRequest.setsPassword(passwordEncoder.encode(storeRequest.getNewPassword()));
                storeMapper.updatePasswordStore(storeRequest);
                return "스토어 비밀번호 수정 성공";

            } else {
                return "비밀번호가 다릅니다.";
            }
        } else {
            return "스토어 정보를 찾지 못했습니다.";
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
