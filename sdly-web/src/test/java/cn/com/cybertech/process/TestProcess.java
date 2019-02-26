package cn.com.cybertech.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * Created by huangkd on 2019/2/18.
 */
public class TestProcess {

    @Test
    public void test(){

        ProcessEngineConfiguration processEngineConfiguration=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcUrl("jdbc:oracle:thin:localhost:1521:orcl");
        processEngineConfiguration.setJdbcUsername("activiti");
        processEngineConfiguration.setJdbcPassword("cyber");
        processEngineConfiguration.setJdbcDriver("oracle.jdbc.OracleDriver");

        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine=processEngineConfiguration.buildProcessEngine();
        System.out.println(processEngine);

    }

}
