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

        try{
            // 존재하는 스토어코드 요청인지 한 번 더 Null 확인
            if(storeMapper.selectStoreByCode(menuRequest.get(0).getSCode()) == null){
                return "존재하는 스토어가 아닙니다.";
            }
            log.info("MenuService ============> getMenuList {}", menuRequest);
            // 인서트 결과처리
            int result = menuMapper.insertMenu(menuRequest);
            // 요청 갯수와 일치하는지 확인
            return result + "개 메뉴 등록 성공";
        } catch (Exception e){
            return  "메뉴 등록에 실피하였습니다.";
        }
    }

    // 메뉴 다건 조회
    public Object selectMenuList(int sCode, String menuCode) {
        try {
            return menuMapper.selectMenuList(sCode, menuCode);
        } catch (Exception e) {
            // 예외가 발생한 경우
            log.error("메뉴 목록 조회 중 오류 발생", e);
            return "메뉴 목록 조회 중 오류 발생";
        }    }

    @Transactional
    // 메뉴 단건 수정
    public Object updateMenu(menuDTO menu) {
        try {
            int result = menuMapper.updateMenu(menu);
            return "200 메뉴 수정 성공";

        } catch (Exception e){
            log.error("Error updating menu. menu: {}", menu, e);
            return "400 메뉴 수정 실패";
        }
    }

    @Transactional
    // 메뉴 다건 수정
    public Object patchMenu(List<menuDTO> menus) {
        try {
            int result = menuMapper.patchMenu(menus);
            return "200 메뉴 수정 성공";

        } catch (Exception e){
            log.error("Error patching menus. menus: {}", menus, e);
            return "400 메뉴 수정 실패";
        }
    }

    @Transactional
    // 메뉴 단건 삭제
    public Object deleteMenu(int sCode, String menuCode) {

        try {
            int result = menuMapper.deleteMenu(sCode, menuCode);
            return "200 메뉴 삭제 성공";
        } catch (Exception e) {
            return "400 메뉴 삭제 실패";
        }
    }
    // 코드 다건 삭제
    @Transactional
    public Object deleteMenuList(int sCode, List<String> codeList) {

        log.info("deleteMenuList ----- {}", codeList);
        try {
            int result = menuMapper.deleteMenuList(sCode, codeList);
            return "200 메뉴 삭제 성공";
        } catch (Exception e) {
            return "400 메뉴 삭제 실패";
        }
    }
}
