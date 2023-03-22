package com.mossflower.antifraud.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mossflower.antifraud.admin.service.AdminFraudSubjectService;
import com.mossflower.antifraud.common.G;
import com.mossflower.antifraud.entity.FraudSubject;
import com.mossflower.antifraud.mapper.FraudSubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/17 9:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminFraudSubjectServiceImpl implements AdminFraudSubjectService {

    @Autowired
    private FraudSubjectMapper fraudSubjectMapper;

    @Override
    public Integer save(FraudSubject fraudSubject) {
        G.removeDecodeJwt();
        LambdaQueryWrapper<FraudSubject> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FraudSubject::getFraudSubjectName, fraudSubject.getFraudSubjectName());
        FraudSubject fs = fraudSubjectMapper.selectOne(lqw);
        if (fs == null) {
            return fraudSubjectMapper.insert(fraudSubject);
        }
        return 2;
    }

    @Override
    public Boolean deleteOne(Long id) {
        G.removeDecodeJwt();
        return fraudSubjectMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean deleteBatch(List<Long> ids) {
        G.removeDecodeJwt();
        return fraudSubjectMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Boolean update(FraudSubject fraudSubject) {
        G.removeDecodeJwt();
        return fraudSubjectMapper.updateById(fraudSubject) > 0;
    }

    @Override
    public List<FraudSubject> getAll() {
        G.removeDecodeJwt();
        return fraudSubjectMapper.selectList(null);
    }

}
