package ping.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import ping.domain.Address;

import javax.sql.DataSource;

public class AddressRepository {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public AddressRepository(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Address create(Address address){
        String sql = "INSERT INTO address " +
                "(customer_id, address_line_1, address_line_2, address_line_3, town, county, post_code) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
/*
        customer_id BIGINT NOT NULL,
        address_line_1 VARCHAR(100),
                address_line_2 VARCHAR(100),
                address_line_3 VARCHAR(100),
                town VARCHAR(50),
                county VARCHAR(50),
                post_code VARCHAR(10)*/
        jdbcTemplate.update(sql,
                address.getCustomerId(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getAddressLine3(),
                address.getTown(),
                address.getCounty(),
                address.getPostCode());

        address.setAddressId(
                jdbcTemplate.queryForObject( "select last_insert_id()", Integer.class)
        );

        return address;
    }
}
