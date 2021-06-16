package win.suroot.dopi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.suroot.dopi.mapper.VerifyTableMapper;
import win.suroot.dopi.po.VerifyTable;

import java.util.Date;

@Service
public class VerifyTableService {

  @Autowired
  private VerifyTableMapper verifyTableMapper;


  public int testMysql() {
    VerifyTable verifyTable = new VerifyTable();
    verifyTable.setCreateTime(new Date());
    verifyTableMapper.insert(verifyTable);
    return verifyTable.getId();
  }
}
