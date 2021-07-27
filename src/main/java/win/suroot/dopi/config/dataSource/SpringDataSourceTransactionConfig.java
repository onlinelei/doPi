package win.suroot.dopi.config.dataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class SpringDataSourceTransactionConfig {


  @Primary
  @Bean("masterDataSourceTransactionManager")
  public DataSourceTransactionManager masterDataSourceTransactionManager(
      @Qualifier("masterDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean("slaveDataSourceTransactionManager")
  public DataSourceTransactionManager slaveDataSourceTransactionManager(
      @Qualifier("slaveDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
