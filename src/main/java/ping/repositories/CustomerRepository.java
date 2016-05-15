package ping.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import ping.domain.Customer;
import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CustomerRepository {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public CustomerRepository(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Customer create(Customer customer){
        String sql = "INSERT INTO customer " +
            "(title, first_name, last_name, email, dob, home_phone, mobile_phone, " +
            "employment_status, employment_start_date, salary_amount) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        jdbcTemplate.update(sql,
                customer.getTitle(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                df.format(customer.getDob()),
                customer.getHomePhone(),
                customer.getMobilePhone(),
                customer.getEmploymentStatus(),
                df.format(customer.getEmploymentStartDate()),
                customer.getSalaryAmount());

        customer.setCustomerId(
                jdbcTemplate.queryForObject( "select last_insert_id()", Integer.class)
        );

        return customer;
    }

}