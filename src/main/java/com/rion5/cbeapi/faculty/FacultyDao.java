package com.rion5.cbeapi.faculty;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FacultyDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Faculty> getFacultyList(){
        String query = """ 
                        select case when 학과 = '' then '경상대학' else 학과 end 학과, 직급,정년트랙구분, 외국인여부 
                        from 교원조회및인사카드출력 
                        where 대학 ='경상대학'and 재직구분 = '재직' and 학기 = 202402
                        ORDER BY array_position(ARRAY['경제학부','경영학부','보험계리학과','회계세무학과'], 학과::text),
                        array_position(ARRAY['전임교원','겸임교수','강사','명예교수'], 직종::text)
                        """;
        List<Faculty> facultyList = namedParameterJdbcTemplate.query(query, (rs, rowNum ) -> new Faculty(
            rs.getString(1),
            rs.getString(2), 
            rs.getString(3), 
            rs.getString(4)
            ));
        return facultyList;
    }
}
