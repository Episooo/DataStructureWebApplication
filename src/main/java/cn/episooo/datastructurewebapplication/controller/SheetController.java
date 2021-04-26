package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.SheetQuestionDAO;
import cn.episooo.datastructurewebapplication.domain.Sheet;
import cn.episooo.datastructurewebapplication.domain.SheetQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/21 23:02
 * @Description：
 */
@RestController
@RequestMapping("/sheet")
public class SheetController extends CommonController<Sheet>{
    @Autowired
    SheetQuestionDAO sheetQuestionDAO;
    @Override
    @GetMapping("/getAll")
    public ResultMsg get(int pagenum, int pagesize) {
        return super.get(pagenum, pagesize);
    }

    @Override
    @PostMapping("/add")
    @Transactional
    public ResultMsg add(@RequestBody Sheet tObj) {
        System.out.println(tObj);
        List<SheetQuestion> questions = tObj.getSheetQuestions();
        tObj.setSheetQuestions(null);

        super.add(tObj);
        for(SheetQuestion sq : questions){
            sq.getId().setSheetId(tObj.getId());
            sheetQuestionDAO.save(sq);
        }

        return ResultMsg.successResult();
    }

    @Override
    @PostMapping("/update")
    public ResultMsg update(Sheet tObj) {
        return super.update(tObj);
    }

    @Override
    @GetMapping("/delete")
    public ResultMsg delete(@RequestParam int id) {
        sheetQuestionDAO.deleteById_SheetId(id);
        return super.delete(id);
    }

    @Override
    @GetMapping("/get/{id}")
    public ResultMsg getOne(@PathVariable int id) {
        return super.getOne(id);
    }
}
