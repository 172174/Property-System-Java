package com.xinyiran.property.controller.ums;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.ams.News;
import com.xinyiran.property.entity.ams.Notice;
import com.xinyiran.property.entity.dto.BindDto;
import com.xinyiran.property.entity.dto.UserOwnerDto;
import com.xinyiran.property.entity.ems.Expense;
import com.xinyiran.property.entity.hms.Owner;
import com.xinyiran.property.entity.rms.Repair;
import com.xinyiran.property.entity.sys.User;
import com.xinyiran.property.entity.tms.Comment;
import com.xinyiran.property.entity.tms.Topic;
import com.xinyiran.property.service.ams.NewsService;
import com.xinyiran.property.service.ams.NoticeService;
import com.xinyiran.property.service.ems.ExpenseService;
import com.xinyiran.property.service.hms.CommunityService;
import com.xinyiran.property.service.hms.HouseService;
import com.xinyiran.property.service.hms.OwnerService;
import com.xinyiran.property.service.rms.RepairService;
import com.xinyiran.property.service.sys.UserService;
import com.xinyiran.property.service.tms.TopicService;
import com.xinyiran.property.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class FrontController extends BaseController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private RepairService repairService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    /**
     * 发帖
     * @param topic
     * @return
     */
    @PostMapping("/topic/release")
    public Result addTopic(@RequestBody() Topic topic){
        if(StringUtils.isNull(topic)){
            return Result.fail(" 系统故障，请稍后再试");
        }
        boolean is = topicService.insertTopic(topic);
        if(!is){
            return Result.fail("发送失败");
        }
        return Result.succ("成功",null);
    }

    /**
     * 回复
     * @param comment
     * @return
     */
    @PostMapping("/topic/comment")
    public Result comment(@RequestBody()Comment comment){
        boolean is = topicService.comment(comment);
        if(!is){
            return Result.fail("发送失败");
        }
        return Result.succ("发送成功",null);
    }


    /**
     * 点赞
     * @param userId
     * @param topicId
     * @return
     */
    @GetMapping("/topic/like/{userId}/{topicId}")
    public Result topicLike(@PathVariable("userId")Long userId,@PathVariable("topicId")Long topicId){
        boolean is = topicService.like(userId,topicId);
        return Result.succ(null);
    }

    /**
     * 帖子列表
     * @param type
     * @return
     */
    @GetMapping("/topic/list/{type}")
    public Result list(@PathVariable("type")Integer type){
        return Result.succ(topicService.getList(type));
    }


    /**
     * 获取帖子详情
     * @param id
     * @return
     */
    @GetMapping("/topic/details/{id}")
    public Result topicDetails(@PathVariable("id")Long id){
        return Result.succ(topicService.getDetails(id));
    }



    /**
     * 用户获取当前用水电气用量
     * @param houseId
     * @return
     */
    @GetMapping("/life/dosage/{id}")
    public Result dosage(@PathVariable("id")Long houseId){
        return Result.succ(houseService.getLifeDosage(houseId));
    }


    /**
     * 获取报修详情
     * @param id
     * @return
     */
    @GetMapping("/repairs/info/{id}")
    public Result repairsInfo(@PathVariable("id")Long id){
        return Result.succ(repairService.getDetail(id));
    }

    /**
     * 查询报修列表
     * @param state
     * @return
     */
    @GetMapping("/repairs/list")
    public Result list(Long houseId,Integer state){
        startPage();
        List<Repair> repairs = null;
        if(state != null && houseId != null){
            repairs = repairService.getPageByUser(houseId,state);
        }
        PageInfo<Repair> pageInfo = new PageInfo<>(repairs);
        return Result.succ(pageInfo);
    }

    /**
     * 创建报修订单
     * @param repair
     * @return
     */
    @PostMapping("/repairs/report")
    public Result report(@RequestBody()Repair repair){
        Boolean is = repairService.creatRepairs(repair);
        if(is){
            return Result.succ("");
        }
        return Result.fail("提交失败，请联系系统管理员！");
    }

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
     * 查找所有公告
     * @return
     */
    @GetMapping("/notice/all")
    public Result all(){
        List<Notice> msgs = noticeService.getAll();
        return Result.succ(msgs);
    }

    /**
     * 查找新闻列表,app
     * @param news
     * @return
     */
    @GetMapping("/news/list")
    public Result page(News news){
        List<News> newsList = newsService.getNews(news);
        return Result.succ(newsList);
    }


    /**
     * 查找最新新闻
     * @param news
     * @return
     */
    @GetMapping("/news/recent")
    public Result recentNews(News news){
        startPage();
        List<News> newsList = newsService.getRecentPage(news);
        PageInfo<News> pageInfo = new PageInfo<>(newsList);
        return Result.succ(pageInfo);
    }

    /**
     * 绑定
     * @param bindDto
     * @return
     */
    @PostMapping("/toBind")
    public Result toBind(@RequestBody()BindDto bindDto){
        if(StringUtils.isEmpty(bindDto.getOwnerName()) || StringUtils.isEmpty(bindDto.getHouseNum()) || StringUtils.isEmpty(bindDto.getIdCard())){
            return Result.succ(201,"请重新填写信息",null);
        }
        Owner owner = ownerService.getOwnerByName(bindDto.getOwnerName());
        System.out.println("owner: "+owner);
        System.out.println("binddto: "+bindDto);
        if(owner == null || !owner.getIdCard().equals(bindDto.getIdCard()) || !owner.getHouseNum().equals(bindDto.getHouseNum())){
            return Result.succ(201,"该住户不存在",null);
        }
        Boolean is = ownerService.bind(bindDto.getUserId(), owner.getId());
        if(is){
            User user = userService.getUserInfoByUserId(bindDto.getUserId());
            UserOwnerDto userOwnerDto = userService.getUserInfoAndOwnerInfoByUserName(user.getUsername());
            return Result.succ("绑定成功",userOwnerDto);
        }
        return Result.succ(201,"绑定失败",null);
    }

    /**
     * 解绑
     * @param userId
     * @param ownerId
     * @return
     */
    @GetMapping("/untie/{userId}/{ownerId}")
    public Result untie(@PathVariable("userId")Long userId,@PathVariable("ownerId")Long ownerId){
        System.out.println(userId+"  "+ownerId);
        if(userId == null || ownerId == null)
            return Result.fail("解绑失败");
        Boolean is = ownerService.untie(userId,ownerId);
        if(is) {
            User user = userService.getUserInfoByUserId(userId);
            UserOwnerDto u = userService.getUserInfoAndOwnerInfoByUserName(user.getUsername());
            return Result.succ("解绑成功",u);
        }

        return Result.fail("解绑失败");
    }


    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/userInfo/{id}")
    public Result userInfo(@PathVariable("id")Long id){
        User user = userService.getUserInfoByUserId(id);
        UserOwnerDto u = userService.getUserInfoAndOwnerInfoByUserName(user.getUsername());
        return Result.succ("成功",u);
    }


    /**
     * 获取用户信息
     * @param ownerId
     * @return
     */
    @GetMapping("/membersInfo/{id}")
    public Result membersInfo(@PathVariable("id")Long ownerId){
        Owner owner = ownerService.getDetails(ownerId);
        System.out.println(owner);
        return Result.succ("成功",owner);
    }


    /**
     * 查找新闻详情
     * @param newsId
     * @return
     */
    @GetMapping("/news/details/{id}")
    public Result details(@PathVariable("id")Long newsId){
        return Result.succ(newsService.getDetails(newsId));
    }

    /**
     * 查找小区信息
     * @param houseId
     * @return
     */
    @GetMapping("/community/info/{id}")
    public Result communityInfo(@PathVariable("id")Long houseId){
        return Result.succ(communityService.getInfoByHouseId(houseId));
    }


    /**
     * 查询物业费用
     * @param houseId
     * @return
     */
    @GetMapping("/expense/{id}")
    public Result expense(@PathVariable("id")Long houseId){
        startPage();
        List<Expense> list = expenseService.getListByHouseId(houseId);
        PageInfo<Expense> pageInfo = new PageInfo<>(list);
        return Result.succ(pageInfo);
    }

    /**
     * 查询费用详情
     * @param expenseId
     * @return
     */
    @GetMapping("/expense/info/{id}")
    public Result info(@PathVariable("id")Long expenseId){
        return Result.succ(expenseService.getDetail(expenseId));
    }


    /**
     * 获得家庭成员
     * @param houseId
     * @return
     */
    @GetMapping("/members/{id}")
    public Result members(@PathVariable("id")Long houseId){
        return Result.succ(ownerService.getMembersByHouseId(houseId));
    }

    /**
     * 支付
     * @param expensId
     * @return
     */
    @GetMapping("/pay/{id}")
    public Result pay(@PathVariable("id")Long expensId){
        Boolean is = expenseService.pay(expensId);
        return Result.succ(null);
    }
}
