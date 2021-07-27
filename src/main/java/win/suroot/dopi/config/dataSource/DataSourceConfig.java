package win.suroot.dopi.config.dataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

  @Primary
  @Bean("masterDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.master")
  public DataSource masterDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean("slaveDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.slave")
  public DataSource slaveDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean("masterJdbcTemplate")
  public JdbcTemplate masterJdbcTemplate() throws Exception {
    JdbcTemplate masterJdbcTemplate = new JdbcTemplate(masterDataSource());
    return masterJdbcTemplate;
  }

  @Bean("slaveJdbcTemplate")
  public JdbcTemplate slaveJdbcTemplate() throws Exception {
    JdbcTemplate slaveJdbcTemplate = new JdbcTemplate(slaveDataSource());
    return slaveJdbcTemplate;
  }
}