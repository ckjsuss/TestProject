# 工程简介
- 自定义注解，nacos+sentinel组件集成 
## RepeatSubmit 注解

@annotation(com.zyzh.annotation.RepeatSubmit)
```
@PostMapping("/fuzzy")
@RepeatSubmit(time = 1)
public Result fuzzy(@RequestBody TestReq req){
    logger.info(JSON.toJSONString(req));
    return Result.success();
}
```

#### windows 脚本
```
@echo off
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.7.jar
```

