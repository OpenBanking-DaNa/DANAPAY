package com.dana.danapay.menu;

import com.dana.danapay.store.StoreDTO;
import com.dana.danapay.store.model.dao.StoreMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class menuService {

    private final MenuMapper menuMapper;
    private final StoreMapper storeMapper;

    @Autowired
    public menuService(MenuMapper menuMapper, StoreMapper storeMapper) {
        this.menuMapper = menuMapper;
        this.storeMapper = storeMapper;
    }

    // batch
    @Transactional
    public Object insertMenu(StoreDTO menuRequest) {

        // 존재하는 스토어코드 요청인지 한 번 더 확인
        if(storeMapper.selectStoreByCode(menuRequest.getsCode()) != null ){

            menuRequest.getMenuList().forEach(item -> item.setSCode(menuRequest.getsCode()));
            log.info("MenuService ============> getMenuList {}", menuRequest.getMenuList());

            // 인서트 결과처리
            int result = menuMapper.insertMenu(menuRequest.getMenuList());
            // 요청 갯수와 일치하는지 확인
            if(result == menuRequest.getMenuList().size()){


                return result + "개 메뉴 등록 성공";
            } else {
                return menuRequest.getMenuList().size() - result + "개 메뉴 등록이 실피하였습니다.";
            }
        } else {
            return "요청 메뉴 등록 실패";
        }
    }
}
