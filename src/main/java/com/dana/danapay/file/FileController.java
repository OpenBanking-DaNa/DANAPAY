package com.dana.danapay.file;

import ch.qos.logback.core.model.Model;
import com.dana.danapay.menu.model.service.menuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/api/file")
public class FileController {

    private final menuService menuservice;

    @Autowired
    public FileController(menuService menuservice) {
        this.menuservice = menuservice;
    }


    // 메뉴 등록 템플릿 연결
    @GetMapping
    public String fileUploadView(Model model){

        return "file/fileupload";
    }
    // 메뉴 batch 등록(대용량 일괄 등록)


    // 스토어 등록
    // 템플릿 연결
    // 스토어 batch 등록(대용량 일괄 등록)


}
