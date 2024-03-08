# 工程简介

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


