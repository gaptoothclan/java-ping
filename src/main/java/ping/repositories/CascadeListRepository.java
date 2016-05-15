package ping.repositories;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import ping.cascade.integration.CascadeInterface;
import ping.cascade.integration.company.CascadeFactory;
import ping.domain.Cascade;
import ping.domain.PingRequestBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CascadeListRepository {

    private static final Logger log = LoggerFactory.getLogger(CascadeListRepository.class);

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    private List<CascadeInterface> cascadeList;
    private PingRequestBody pingRequestBody;

    public CascadeListRepository(DataSource dataSource, PingRequestBody pingRequestBody){
        this.cascadeList = new ArrayList<CascadeInterface>();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.pingRequestBody = pingRequestBody;
    }

    public List<CascadeInterface> getCascadeList(){

        CascadeFactory cascadeFactory = new CascadeFactory();

        String sqlQuery = "SELECT c.cascade_id, c.cascade_name, c.cascade_object " +
                "FROM account a " +
                "JOIN account_cascade_order aco " +
                "ON a.account_id = aco.account_id " +
                "JOIN cascade c " +
                "ON aco.cascade_id = c.cascade_id " +
                "WHERE a.api_key = ? " +
                "ORDER BY aco.cascade_order";

        log.info("Start sql");

        cascadeList = jdbcTemplate.query(sqlQuery,  new Object[]{ pingRequestBody.getRequestConfig().getApiKey() },
                (rs, row) -> {
                    Cascade cascade = new Cascade(rs.getInt("cascade_id"), rs.getString("cascade_name"), rs.getString("cascade_object"));
                    return cascadeFactory.getCascade( cascade );
                }
        );


        return cascadeList;
    }
}
