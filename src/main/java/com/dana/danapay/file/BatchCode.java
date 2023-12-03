package com.dana.danapay.file;

import com.dana.danapay.menu.model.dao.MenuMapper;
import com.dana.danapay.menu.model.dto.menuDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class BatchCode {

    PreparedStatement pstmt;
    Connection con;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    // 메뉴 배치 인서트
    @Transactional
    public void menuInsert(List<menuDTO> menuList, int sCode) throws SQLException {

        con = null;
        pstmt = null;
        String sql = "INSERT INTO MENU(MENU_CODE, MENU_NAME, MENU_PRICE, ISORDER, S_CODE) "
                            + "VALUES (MENU_SEQ.NEXTVAL,?,?,?,?)";

        log.info("BatchCode ----- {}, {}, {}, {}", driverClassName, url, username, password);
        try {

            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);

            // 자동 커밋 비활성화
            con.setAutoCommit(false);

            pstmt = con.prepareStatement(sql);

            int count = 0;

            for(menuDTO menu : menuList){
                count++;

                pstmt.setString(1, menu.getMenuName());
                pstmt.setInt(2, menu.getMenuPrice());
                pstmt.setString(3, menu.getIsOrder());
                pstmt.setInt(4, sCode);

                pstmt.addBatch();
                pstmt.clearParameters();

                // 100 개 마다 커밋
                if((count%100) == 0){
                    pstmt.executeBatch();
                    pstmt.clearBatch();
                    con.commit();
                }
            }

            pstmt.executeBatch();
            con.commit();

        } catch (Exception e) {
            con.rollback();
        } finally {
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
        }

    }
    

}
