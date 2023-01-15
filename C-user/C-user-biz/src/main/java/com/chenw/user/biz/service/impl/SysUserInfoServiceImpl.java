package com.chenw.user.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenw.base.common.core.context.SecurityContextHolder;
import com.chenw.base.common.core.utils.PasswordEncoderUtil;
import com.chenw.user.api.entity.SysUserInfo;
import com.chenw.user.biz.dao.SysUserInfoDao;
import com.chenw.user.biz.service.SysUserInfoService;
import com.chenw.user.biz.vo.InsertOrUpdateSysUserInfoVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenw
 * @since 2023-01-07
 */
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoDao, SysUserInfo> implements SysUserInfoService {

    @Override
    public String insertSysUserInfo(InsertOrUpdateSysUserInfoVO vo) {
        String salt = PasswordEncoderUtil.generateSalt();
        SysUserInfo sysUserInfo = new SysUserInfo();
        sysUserInfo.setAccount(vo.getAccount());
        sysUserInfo.setGender(vo.getGender());
        sysUserInfo.setAvatar(vo.getAvatar());
        sysUserInfo.setMobile(vo.getMobile());
        sysUserInfo.setCreateBy(SecurityContextHolder.getUserId());
        sysUserInfo.setSalt(salt);
        sysUserInfo.setPassword(PasswordEncoderUtil.generatePassword(vo.getPassword(),salt));
        baseMapper.insert(sysUserInfo);
        return sysUserInfo.getId();
    }
}
