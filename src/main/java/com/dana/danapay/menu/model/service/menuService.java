package com.dana.danapay.menu.model.service;

import com.dana.danapay.menu.model.dao.MenuMapper;
import com.dana.danapay.menu.model.dto.menuDTO;
import com.dana.danapay.store.model.dao.StoreMapper;
import lombok.extern.slf4j.Slf4j;
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

    // 메뉴 다건 등록
    @Transactional
    public Object insertMenu(List<menuDTO> menuRequest) {

        // 존재하는 스토어코드 요청인지 한 번 더 확인
        if(storeMapper.selectStoreByCode(menuRequest.get(0).getSCode()) != null ){

            log.info("MenuService ============> getMenuList {}", menuRequest);

            // 인서트 결과처리
            int result = menuMapper.insertMenu(menuRequest);
            // 요청 갯수와 일치하는지 확인
            if(result == menuRequest.size()){

                return result + "개 메뉴 등록 성공";
            } else {
                return menuRequest.size() - result + "개 메뉴 등록이 실피하였습니다.";
            }
        } else {
            return "요청 메뉴 등록 실패";
        }
    }

    // 메뉴 다건 조회
    public Object selectMenuList(int sCode, String menuCode) {

        return menuMapper.selectMenuList(sCode, menuCode);
    }

    @Transactional
    // 메뉴 단건 수정
    public Object updateMenu(menuDTO menu) {

        int result = menuMapper.updateMenu(menu);

        if(result > 0) {

            return "200 메뉴 수정 성공 : " + result +"개";
        } else {
            return "400 메뉴 수정 실패";
        }
    }

    @Transactional
    // 메뉴 다건 수정
    public Object patchMenu(List<menuDTO> menus) {

        int result = menuMapper.patchMenu(menus);

        System.out.println("menus = " + menus);

        if(result > 0) {

            return "200 메뉴 수정 성공 : " + result +"개";
        } else {
            return "400 메뉴 수정 실패 : " + result +"개";
        }
    }

    @Transactional
    // 메뉴 단건 삭제
    public Object deleteMenu(int sCode, String menuCode) {

        int result = menuMapper.deleteMenu(sCode, menuCode);

        if(result > 0) {

            return "200 메뉴 삭제 성공 : " + result +"개";
        } else {
            return "400 메뉴 삭제 실패";
        }
    }

    // 코드 다건 삭제
    @Transactional
    public Object deleteMenuList(int sCode, List<String> codeList) {

        log.info("deleteMenuList ----- {}", codeList);
        int result = menuMapper.deleteMenuList(sCode, codeList);

        if(result > 0) {

            return "200 메뉴 삭제 성공 : " + result +"개";
        } else {
            return "400 메뉴 삭제 실패 : " + result +"개";
        }
    }
}
