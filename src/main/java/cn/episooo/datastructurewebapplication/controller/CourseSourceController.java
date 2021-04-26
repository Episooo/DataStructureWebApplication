package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.domain.CourseSource;
import cn.episooo.datastructurewebapplication.utils.FileUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/21 20:29
 * @Description：
 */
@RestController
@RequestMapping("/courseSource")
public class CourseSourceController extends CommonController<CourseSource> {




    @Override
    @GetMapping("/getAll")
    public ResultMsg get(int pagenum, int pagesize) {
        return super.get(pagenum, pagesize);
    }


    @PostMapping("/upload")
    @Transactional
    public ResultMsg upload(@RequestParam int courseId, @RequestParam MultipartFile file)  throws IOException {
        String path = courseId + "/";
        FileUtil.saveFile(path,file);
        //保存数据库对象
        dao.save(new CourseSource(courseId,file.getOriginalFilename(),path));
        return ResultMsg.successResult("上传成功");
    }


    @Override
    @PostMapping("/delete")
    @Transactional
    public ResultMsg delete(@RequestParam int id) {
        CourseSource source = dao.getOne(id);
        dao.deleteById(id);
        FileUtil.deleteFile(source);
        return ResultMsg.successResult();
    }

    @GetMapping("/get/{id}")
    public void getFile(@PathVariable int id, HttpServletResponse res) throws IOException{
        CourseSource source = dao.getOne(id);
        FileUtil.downloadFile(res,source);
    }

}
