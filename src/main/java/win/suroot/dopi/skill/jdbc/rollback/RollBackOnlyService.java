package win.suroot.dopi.skill.jdbc.rollback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class RollBackOnlyService {

  @Autowired
  @Qualifier("masterJdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  @Transactional(rollbackFor = Exception.class)
  public void rollBackTransaction() {
    String sql1 = "update t_user set money = money + 50 where id = 1";
    String sql2 = "update t_user set money = money - 50 where id = 2";
    jdbcTemplate.execute(sql1);
    jdbcTemplate.execute(sql2);

    double a = 1 / 0;
  }
}
