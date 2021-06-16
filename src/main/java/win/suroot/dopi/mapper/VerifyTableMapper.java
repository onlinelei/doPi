package win.suroot.dopi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import win.suroot.dopi.po.VerifyTable;
import win.suroot.dopi.po.VerifyTableExample;

@Mapper
public interface VerifyTableMapper {
  int countByExample(VerifyTableExample example);

  int deleteByExample(VerifyTableExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(VerifyTable record);

  int insertSelective(VerifyTable record);

  List<VerifyTable> selectByExample(VerifyTableExample example);

  VerifyTable selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") VerifyTable record, @Param("example") VerifyTableExample example);

  int updateByExample(@Param("record") VerifyTable record, @Param("example") VerifyTableExample example);

  int updateByPrimaryKeySelective(VerifyTable record);

  int updateByPrimaryKey(VerifyTable record);
}