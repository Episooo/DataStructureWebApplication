package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.domain.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/2/11 13:05
 * @Description：
 */
@RestController
@RequestMapping("/tag")
public class TagController extends CommonController<Tag> {

    @RequestMapping("/getAll")
    public ResultMsg getAll(int pagenum, int pagesize){
        return get(pagenum,pagesize);
    }

    @RequestMapping("/getTags")
    public ResultMsg getTags(){
        return ResultMsg.successResult(dao.findAll());
    }

    @Override
    @RequestMapping("/get/{id}")
    public ResultMsg getOne(@PathVariable int id) {
        return super.getOne(id);
    }

    @Override
    @RequestMapping("/add")
    public ResultMsg add(Tag tObj) {
        return super.add(tObj);
    }

    @Override
    @RequestMapping("/update")
    public ResultMsg update(Tag tObj) {
        return super.update(tObj);
    }

    @Override
    @RequestMapping("/delete")
    public ResultMsg delete(@RequestParam int id) {
        return super.delete(id);
    }
}
