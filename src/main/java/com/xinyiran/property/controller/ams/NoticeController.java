package com.xinyiran.property.controller.ams;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.ams.News;
import com.xinyiran.property.entity.ams.Notice;
import com.xinyiran.property.service.ams.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/ams")
public class NoticeController extends BaseController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 公告列表查询
     * @param notice
     * @return
     */
    @GetMapping("/notice/page")
    public Result page(Notice notice){
        startPage();
        List<Notice> noticeList = noticeService.getPage(notice);
        PageInfo<Notice> pageInfo = new PageInfo<>(noticeList);
        return Result.succ(pageInfo);
    }


    /**
     * 查找最新公告
     * @return
     */
    @GetMapping("/notice/msg")
    public Result msg(){
        Notice msg = noticeService.getLatestNotice();
        return Result.succ(msg);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/notice/delete/{id}")
    public Result delete(@PathVariable("id")Long id){
        Boolean delete = noticeService.delete(id);
        if(delete)
            return Result.succ(null);
        return Result.succ(201,"删除失败",null);
    }


    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/notice/info/{id}")
    public Result info(@PathVariable("id")Long id){
        Notice notice = noticeService.getDetails(id);
        return Result.succ(notice);
    }

    @PostMapping("/notice/save")
    public Result send(@RequestBody()Notice notice){
        System.out.println(notice);
        Boolean is =noticeService.send(notice);
        if(is)
            return Result.succ(null);
        return Result.succ(201,"发送失败",null);
    }

    @PostMapping("/notice/update")
    public Result update(@RequestBody()Notice notice){
        System.out.println(notice);
        Boolean is =noticeService.send(notice);
        if(is)
            return Result.succ(null);
        return Result.succ(201,"发送失败",null);
    }
}
