package com.dana.danapay.menu;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.store.StoreDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/menu")
public class menuController {

    private final menuService menuservice;

    @Autowired
    public menuController(menuService menuservice) {
        this.menuservice = menuservice;
    }

    @PostMapping("/regist")
    public ResponseEntity<ResponseDTO> registMenu(@RequestBody StoreDTO menuRequest) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "메뉴 등록",
                menuservice.insertMenu(menuRequest)));
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> menuList(@RequestParam int sCode){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "메뉴 조회",
                menuservice.selectMenuList(sCode)));

    }
}
