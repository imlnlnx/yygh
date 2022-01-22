package com.ln.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ln.yygh.common.exception.HospitalException;
import com.ln.yygh.common.result.Result;
import com.ln.yygh.hosp.service.HospitalSetService;
import com.ln.yygh.model.hosp.HospitalSet;
import com.ln.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import java.util.List;
import java.util.Random;

/**
 * @Description:
 * @Author: Euphratica
 * @Date: 2022/01/17
 */
@Api(tags = "医院设置服务")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation("发送签名密钥")
    @GetMapping("sendKey/{id}")
    public Result sendKey(@PathVariable long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        if (hospitalSet != null) {
            String signKey = hospitalSet.getSignKey();
            String hosCode = hospitalSet.getHosCode();
            //TODO 发送短信
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("医院设置锁定和解锁")
    @PutMapping("lock/{id}/{status}")
    public Result lockHospitalSet(@PathVariable long id, @PathVariable byte status) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        if (hospitalSet != null) {
            hospitalSet.setStatus(status);
            return hospitalSetService.updateById(hospitalSet) ? Result.ok() : Result.fail();
        }
        return Result.fail();
    }

    @ApiOperation("添加医院设置")
    @PostMapping("save")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {

        // 生成签名秘钥
        String key = System.currentTimeMillis() + new Random().nextInt(1000) + "";
        String md5Key = DigestUtils.md5DigestAsHex(key.getBytes());
        hospitalSet.setSignKey(md5Key);

        // 设置状态，1 表示可用
        hospitalSet.setStatus((byte)1);

        // 添加
        boolean flag = hospitalSetService.save(hospitalSet);
        return flag ? Result.ok() : Result.fail();
    }

    @ApiOperation("根据 id 获取医院设置")
    @GetMapping("{id}")
    public Result getHospitalSetById(@PathVariable Long id) {
        HospitalSet res = hospitalSetService.getById(id);
        return res==null ? Result.fail() : Result.ok(res);
    }

    @ApiOperation("修改医院设置")
    @PutMapping("update")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        return hospitalSetService.updateById(hospitalSet) ? Result.ok() : Result.fail();
    }

    @ApiOperation("批量逻辑删除医院设置")
    @DeleteMapping("batch")
    public Result removeBatchHospitalSet(@RequestBody List<Long> ids) {
        return hospitalSetService.removeByIds(ids) ? Result.ok() : Result.fail();
    }

    @ApiOperation("带条件的分页查询")
    @PostMapping("page/{current}/{size}")
    public Result<Page<HospitalSet>> findPageHospitalSet(@PathVariable Long current,
                                      @PathVariable Long size,
                                      @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        // 分页条件
        Page<HospitalSet> page = new Page<>(current, size);

        // 查询条件
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        if (hospitalSetQueryVo != null) {
            String hosName = hospitalSetQueryVo.getHosName();
            String hosCode = hospitalSetQueryVo.getHosCode();
            if (!StringUtils.isEmpty(hosName)) {
                queryWrapper.eq("hosname", hosName);
            }
            if (!StringUtils.isEmpty(hosCode)) {
                queryWrapper.eq("hoscode", hosCode);
            }
        }

        Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, queryWrapper);

        return Result.ok(hospitalSetPage);
    }

    @ApiOperation("逻辑删除医院设置信息")
    @DeleteMapping("{id}")
    public Result removeHospitalSetById(@PathVariable int id) {
        return hospitalSetService.removeById(id) ? Result.ok() : Result.fail();
    }

    @ApiOperation("获取所有医院设置信息")
    @GetMapping("all")
    public Result<List<HospitalSet>> getAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }
}
