package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.domain.ID;
import cn.episooo.datastructurewebapplication.utils.IJPA;
import cn.episooo.datastructurewebapplication.utils.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/21 19:44
 * @Description：
 */

public class CommonController<T extends ID> {

    @Autowired
    protected IJPA<T> dao;

//    @GetMapping("/getAll")
    public ResultMsg get(int pagenum, int pagesize){
        return ResultMsg.successResult(dao.findAll(PageRequest.of(pagenum-1,pagesize)));
    }

//    @PostMapping("/add")
    public ResultMsg add(T tObj){
        dao.save(tObj);
        return ResultMsg.successResult();
    }

//    @PostMapping("/update")
    public ResultMsg update(T tObj){
        UpdateUtil.updateById(dao,tObj);
        return ResultMsg.successResult();
    }

//    @PostMapping("/delete")
    public ResultMsg delete(@RequestParam int id){
        dao.deleteById(id);
        return ResultMsg.successResult();
    }

    //@GetMapping("/get/{id}")
    public ResultMsg getOne(@PathVariable int id){
        return ResultMsg.successResult(dao.findById(id));
    }
}
