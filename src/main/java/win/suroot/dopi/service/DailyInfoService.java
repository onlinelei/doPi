package win.suroot.dopi.service;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import win.suroot.dopi.dto.DailyInfoDTO;
import win.suroot.dopi.mapper.DailyInfoMapper;
import win.suroot.dopi.po.DailyInfo;
import win.suroot.dopi.po.DailyInfoExample;
import win.suroot.dopi.utils.DateFormatConstant;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class DailyInfoService {

    @Autowired
    private DailyInfoMapper dailyInfoMapper;

    public DailyInfoDTO todayInfo() {
        DailyInfoExample example = new DailyInfoExample();
        DailyInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDateStrEqualTo(DateFormatUtils.format(new Date(), DateFormatConstant.yyyMMdd));

        List<DailyInfo> dailyInfos = dailyInfoMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(dailyInfos)) {
            DailyInfoDTO dailyInfoDTO = new DailyInfoDTO();
            BeanUtils.copyProperties(dailyInfos.get((0)), dailyInfoDTO);
            return dailyInfoDTO;
        }
        return new DailyInfoDTO();
    }
}
