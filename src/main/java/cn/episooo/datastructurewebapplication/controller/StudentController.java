package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.StudentDAO;
import cn.episooo.datastructurewebapplication.domain.Student;
import cn.episooo.datastructurewebapplication.utils.MD5Util;
import cn.episooo.datastructurewebapplication.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/26 22:51
 * @Description：
 */
@RestController
@RequestMapping("/student")
public class StudentController extends CommonController<Student>{
    @Autowired
    private StudentDAO dao ;

    @RequestMapping("/getAll")
    public ResultMsg get(String query, int classId, int pagenum, int pagesize) {
        PageRequest pageRequest = PageRequest.of(pagenum - 1, pagesize);
        if(!StringUtil.isEmpty(query)){
            return ResultMsg.successResult(dao.findByStudentClassIdAndNameLike(classId,"%"+query+"%", pageRequest));
        }
        return ResultMsg.successResult(dao.findByStudentClassId(classId,PageRequest.of(pagenum - 1, pagesize)));
    }

    @Override
    @RequestMapping("/add")
    public ResultMsg add(@RequestBody Student tObj) {
        if(tObj.getPassword()==null) return ResultMsg.failedResult("password can't be empty");
        tObj.setPassword(MD5Util.getMD5Str(tObj.getPassword()));
        return super.add(tObj);
    }

    @Override
    @RequestMapping("/update")
    public ResultMsg update(@RequestBody Student tObj) {
        if(tObj.getPassword()!=null){
            tObj.setPassword(MD5Util.getMD5Str(tObj.getPassword()));
        }
        return super.update(tObj);
    }

    @Override
    @RequestMapping("/delete")
    public ResultMsg delete(@RequestParam int id) {
        return super.delete(id);
    }

    @Override
    @RequestMapping("/get")
    public ResultMsg getOne(@RequestParam int id) {
        return super.getOne(id);
    }


}
