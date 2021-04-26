package cn.episooo.datastructurewebapplication.dao;

import cn.episooo.datastructurewebapplication.vo.ClassSheetAnswerInfo;
import cn.episooo.datastructurewebapplication.vo.ClassSheetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/4/11 11:46
 * @Description：
 */
@Repository
public class AnalysisDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ClassSheetVO> getClassSheetTable(int classId,int sheetId){
        String sql =
                " select student_username,student_name,subject,ifnull(score,-1) as score,total from " +
                " ( " +
                " SELECT student_id,student_username,student_name,subject,total,sid " +
                " from student a left join " +
                " ( select s.sheet_id as sid,s.subject as subject,sum(score) as total,class_id from " +
                " (select sheet_id,subject,class_id from sheet left join sheet_class on sheet_id = sheet.id where class_id = ? ) s " +
                " left join sheet_question on sheet_question.sheet_id = s.sheet_id group by s.sheet_id) b " +
                " on a.class_id = b.class_id" +
                " where sid = ?" +
                " ) z left join answer_sheet on answer_sheet.stu_id = z.student_id and answer_sheet.sheet_id = ?; ";
        List<ClassSheetVO> query = jdbcTemplate.query(sql, rowMapperClassSheetVO(), new Object[]{classId, sheetId, sheetId});
        return query;
    }
    public RowMapper<ClassSheetVO> rowMapperClassSheetVO(){
        return new RowMapper<ClassSheetVO>(){
            @Override
            public ClassSheetVO mapRow(ResultSet resultSet, int i) throws SQLException {
                ClassSheetVO res = new ClassSheetVO();
                res.setStudentUsername(resultSet.getInt("student_username"));
                res.setStudentName(resultSet.getString("student_name"));
                res.setSubject(resultSet.getString("subject"));
                res.setScore(resultSet.getDouble("score"));
                res.setTotal(resultSet.getDouble("total"));
                if(res.getScore()!=-1){
                    res.setRate(res.getScore()/res.getTotal());
                }
                return res;
            }
        };
    }

    public List<ClassSheetAnswerInfo> getClassSheetAnswerInfo(int classId,int sheetId){
        String sql =
                "select 题号, question.subject as 题目,平均得分,总分 from " +
                "( " +
                "select q.number as 题号,q.question_id,q.score as 总分, avg(a.score) as 平均得分 from sheet_question q " +
                "left join answer_sheet_answer a on q.sheet_id = a.sheet_id and q.number = a.ans_number where a.sheet_id = ? and " +
                        "stu_id in(select student_id from student where class_id = ? ) group by q.number " +
                ") qa left join question on qa.question_id=question.id ;" ;
        List<ClassSheetAnswerInfo> query = jdbcTemplate.query(sql, rowMapperClassSheetAnswerInfoVO(), new Object[]{sheetId,classId});
        return query;
    }
    public RowMapper<ClassSheetAnswerInfo> rowMapperClassSheetAnswerInfoVO(){
        return new RowMapper<ClassSheetAnswerInfo>(){
            @Override
            public ClassSheetAnswerInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                ClassSheetAnswerInfo res = new ClassSheetAnswerInfo();
                res.setNumber(resultSet.getInt("题号"));
                res.setSubject(resultSet.getString("题目"));
                res.setAvgScore(resultSet.getDouble("平均得分"));
                res.setTotal(resultSet.getDouble("总分"));
                return res;
            }
        };
    }
}
