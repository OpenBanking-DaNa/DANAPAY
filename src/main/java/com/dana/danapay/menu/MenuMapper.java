package com.dana.danapay.menu;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    int insertMenu(List<menuDTO> menuRequest);

    List<menuDTO> selectMenuList(int sCode, String menuCode);

    int updateMenu(menuDTO menu);

    int patchMenu(List<menuDTO> menus);

    int deleteMenu(int sCode, String menuCode);
}
