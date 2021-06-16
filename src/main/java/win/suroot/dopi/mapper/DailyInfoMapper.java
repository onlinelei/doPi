package win.suroot.dopi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import win.suroot.dopi.po.DailyInfo;
import win.suroot.dopi.po.DailyInfoExample;

@Mapper
public interface DailyInfoMapper {
  int countByExample(DailyInfoExample example);

  int deleteByExample(DailyInfoExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(DailyInfo record);

  int insertSelective(DailyInfo record);

  List<DailyInfo> selectByExample(DailyInfoExample example);

  DailyInfo selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") DailyInfo record, @Param("example") DailyInfoExample example);

  int updateByExample(@Param("record") DailyInfo record, @Param("example") DailyInfoExample example);

  int updateByPrimaryKeySelective(DailyInfo record);

  int updateByPrimaryKey(DailyInfo record);
}