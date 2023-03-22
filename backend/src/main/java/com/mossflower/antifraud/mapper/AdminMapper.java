package com.mossflower.antifraud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mossflower.antifraud.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author z's'b
 * @version 1.0
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
