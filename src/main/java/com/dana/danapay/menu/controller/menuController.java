package com.dana.danapay.menu.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.menu.model.dto.menuDTO;
import com.dana.danapay.menu.model.service.menuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/menu/{sCode}") // 스토어 코드별
public class menuController {

    private final menuService menuservice;

    @Autowired
    public menuController(menuService menuservice) {
        this.menuservice = menuservice;
    }

    // 메뉴 다건 등록
    @PreAuthorize("hasAnyRole('STORE','ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseDTO> insertMenu(@PathVariable int sCode, @RequestBody List<menuDTO> menuRequest) {

        menuRequest.stream().forEach(menu -> menu.setSCode(sCode));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "메뉴 다건 등록",
                menuservice.insertMenu(menuRequest)));
    }



    // 메뉴 단건 조회
    @GetMapping("/{menuCode}")
    public ResponseEntity<ResponseDTO> selectMenu(@PathVariable int sCode, @PathVariable String menuCode){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "메뉴 목록 조회",
                menuservice.selectMenuList(sCode, menuCode)));
    }

    // 메뉴 다건 조회
    @GetMapping
    public ResponseEntity<ResponseDTO> selectMenu(@PathVariable int sCode){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "메뉴 목록 조회",
                menuservice.selectMenuList(sCode, null)));
    }

    // 메뉴 단건 수정
    @PreAuthorize("hasAnyRole('STORE','ADMIN')")
    @PutMapping("/{menuCode}")
    public ResponseEntity<ResponseDTO> updateMenu(@PathVariable int sCode,
                                                @PathVariable String menuCode,
                                                @RequestBody menuDTO menu){
        menu.setSCode(sCode);
        menu.setMenuCode(menuCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "메뉴 수정",
                menuservice.updateMenu(menu)));
    }
    @PreAuthorize("hasAnyRole('STORE','ADMIN')")
    @PatchMapping
    public ResponseEntity<ResponseDTO> patchMenu(@PathVariable int sCode,
                                                  @RequestBody List<menuDTO> menus){

        menus.forEach(menu -> menu.setSCode(sCode));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "메뉴 수정",
                menuservice.patchMenu(menus)));
    }

    // 메뉴 단건 삭제
    @PreAuthorize("hasAnyRole('STORE','ADMIN')")
    @DeleteMapping("/{menuCode}")
    public ResponseEntity<ResponseDTO> deleteMenu(@PathVariable int sCode,
                                                  @PathVariable String menuCode){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "메뉴 단건 삭제",
                menuservice.deleteMenu(sCode, menuCode)));
    }

    // 메뉴 단건/다건 삭제
    @PreAuthorize("hasAnyRole('STORE','ADMIN')")
    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteMenuList(@PathVariable int sCode,
                                                  @RequestParam String codes){

        List<String> codeList = Arrays.asList(codes.split(","));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "메뉴 다건 삭제",
                menuservice.deleteMenuList(sCode, codeList)));
    }


}
