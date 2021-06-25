package junit.springTest;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import win.suroot.dopi.mapper.VerifyTableMapper;
import win.suroot.dopi.po.VerifyTable;
import win.suroot.dopi.po.VerifyTableExample;

import java.util.List;

@Slf4j
public class TestJunitWithApplication extends BaseSpringJunit4ClassTest {

  @Autowired
  private VerifyTableMapper verifyTableMapper;

  @Disabled
  @Test
  public void testTransactionalRollback() {
    VerifyTableExample example = new VerifyTableExample();
    example.createCriteria().andIdEqualTo(1);
    List<VerifyTable> verifyTables = verifyTableMapper.selectByExample(example);
    log.info("result:{}", JSONObject.toJSONString(verifyTables));
  }
}
