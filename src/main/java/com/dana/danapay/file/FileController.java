package com.dana.danapay.file;

import ch.qos.logback.core.model.Model;
import com.dana.danapay.auth.model.service.AuthService;
import com.dana.danapay.menu.model.dto.menuDTO;
import com.dana.danapay.menu.model.service.menuService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dana.danapay.security.JwtFilter.getUserID;


@Slf4j
@Controller
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    private final menuService menuservice;
    private final AuthService authService;
    private final BatchCode batchCode;


    // 메뉴 등록 템플릿 연결
    @GetMapping
    public String fileUploadView(Model model){

        return "file/fileupload";
    }

    // 메뉴 엑셀파일 전송받기 - batch 등록(대용량 일괄 등록)
//    @ResponseBody
    @PostMapping("/menu/{sCode}")
    public String batchAddMenuList(
            @PathVariable int sCode,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            final MultipartHttpServletRequest multiRequest,
            ModelMap model) throws Exception {


        log.info("batchAddMenuList --------{}", multiRequest);

        // 결과를 담아 뷰에 전달할 맵
        Map<String, Object> resMap = new HashMap<String, Object>();

        try{

            // 엑셀파일 처리를 위한 객체 생성
            ExcelRequestManager em = new ExcelRequestManager();
            // 멀티파트 요청에서 파일 맵을 가져옴
            final Map<String, MultipartFile> files = multiRequest.getFileMap();

            // 엑셀파일 해석을 통한 데이터 추출하여 리스트로 반환 받아옴
            List<HashMap<String,String>> apply =null;

            apply = em.parseExcelMultiPart(files,"menu_", 0, getUserID, "menu");

            // 디비에 저장할 메뉴 리스트 객체 생성
            List<menuDTO> menuList = new ArrayList<>();
            menuDTO menu = new menuDTO();
            // 추출된 리스트 데이터를 기반으로 메뉴를 추가
            for(int i = 0; i < apply.size(); i++){

                // 각 열의 데이터를 객체에 저장
                menu.setMenuName(apply.get(i).get("cell_0"));
                menu.setMenuPrice(Integer.parseInt(apply.get(i).get("cell_1")));
                menu.setIsOrder(apply.get(i).get("cell_2"));

                menuList.add(menu);
                // 메뉴 추가 서비스 진행 - 배치 처리
            }

            log.info("batchAddMenuList menuList.size--------{}", menuList.size());

            batchCode.menuInsert(menuList, sCode);

            // 처리 성공시 맵에 결과 및 메세지 설정
            resMap.put("res", "ok");
            resMap.put("msg", "txt.success");


        }catch(Exception e){
            System.out.println(e.toString());
            resMap.put("res", "error");
            resMap.put("msg", "txt.fail");
        }

        // 리다이렉트 시 맵을 플래시 어트리뷰트로 추가하고 메뉴 페이지로 리다이렉트
        redirectAttributes.addFlashAttribute("resMap", resMap);
        return "redirect:/api/menu/" + sCode;
    }



    // 스토어 등록
    // 템플릿 연결
    // 스토어 batch 등록(대용량 일괄 등록)


}
