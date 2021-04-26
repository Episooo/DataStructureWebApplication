package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.annotation.Authorization;
import cn.episooo.datastructurewebapplication.annotation.CurrentStudent;
import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.StudentDAO;
import cn.episooo.datastructurewebapplication.dao.TeacherDAO;
import cn.episooo.datastructurewebapplication.domain.Student;
import cn.episooo.datastructurewebapplication.domain.Teacher;
import cn.episooo.datastructurewebapplication.utils.MD5Util;
import cn.episooo.datastructurewebapplication.utils.VerifyCode;
import cn.episooo.datastructurewebapplication.utils.security.JwtObject;
import cn.episooo.datastructurewebapplication.utils.security.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/7 20:07
 * @Description：
 */
@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private TeacherDAO teacherDAO;

    private static String VERIFY_CODE = "verifyCode";


    @Authorization(type = TokenType.Teacher)
    @PostMapping("/add")
    public ResultMsg addStudent(Student student){
        student.setId(null);
        studentDAO.save(student);
        return ResultMsg.successResult();
    }

    @GetMapping("/getAll")
    public ResultMsg getAll(){
        List<Student> all = studentDAO.findAll();
        for(Student stu : all){
            stu.setPassword(null);
        }
        return ResultMsg.successResult(all);
    }

//    @GetMapping("/get/{id}")
//    public ResultMsg getStudent(@PathVariable int id){
//        Student stu = studentDAO.findById(id).get();
//        stu.setPassword(null);
//        return ResultMsg.successResult(stu);
//    }
    @RequestMapping("/verifycode")
    public void verifycode(HttpSession session, HttpServletResponse response) {
        System.out.println(session.getId());
        VerifyCode vc = new VerifyCode();
        BufferedImage bi = vc.getImage();
        try {
            VerifyCode.output(bi, response.getOutputStream());
            session.setAttribute(VERIFY_CODE, vc.getCode());
            System.out.println(session.getAttribute(VERIFY_CODE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/login")
    public ResultMsg login(@RequestParam String username,@RequestParam String password,String verifyCode,HttpSession session){
        System.out.println(session.getId());
        System.out.println(session.getAttribute(VERIFY_CODE));
        if(!VerifyCode.isCorrect(verifyCode,session.getAttribute(VERIFY_CODE))){
            session.setAttribute(VERIFY_CODE,null);
            return ResultMsg.failedResult(-1,"验证码错误");
        }
        HashMap<String, Object> map = new HashMap<>();
        password = MD5Util.getMD5Str(password);
        Student stu = studentDAO.findByUsernameAndPassword(username, password);
        if(stu != null){
            JwtObject jwtObject = new JwtObject(TokenType.Student, stu.getId());
            map.put("name",stu.getName());
            map.put("role",1);
            map.put("token",jwtObject.toJwtString());
            return ResultMsg.successResult(map);
        }
        Teacher teacher = teacherDAO.findByUsernameAndPassword(username, password);
        if(teacher != null){
            JwtObject jwtObject = new JwtObject(TokenType.Teacher, teacher.getId());
            map.put("name",teacher.getName());
            map.put("role",2);
            map.put("token",jwtObject.toJwtString());
            return ResultMsg.successResult(map);
        }
        return ResultMsg.failedResult("用户名或密码错误");
    }

    @PostMapping("/changePassword")
    @Authorization(type = TokenType.Student)
    public ResultMsg updateStudentPassword(@CurrentStudent(formDAO = true) Student student,@RequestParam String oldPass,@RequestParam String pass){
        System.out.println(student.getPassword());
        System.out.println(MD5Util.getMD5Str(oldPass));
        if(MD5Util.getMD5Str(oldPass).equals(student.getPassword())){
            student.setPassword(MD5Util.getMD5Str(pass));
            studentDAO.save(student);
        }else {
            return ResultMsg.failedResult("旧密码不正确");
        }
        return ResultMsg.successResult();
    }



    @GetMapping("/delete")
    public ResultMsg deleteStudent(@RequestParam int id){
        studentDAO.deleteById(id);
        return ResultMsg.successResult();
    }

    @GetMapping("/refreshToken")
    public ResultMsg refreshToken(String token){
        JwtObject jwtObject = JwtObject.parseToken(token);
        jwtObject.refresh();
        return ResultMsg.successResult(jwtObject.toJwtString());
    }

}
