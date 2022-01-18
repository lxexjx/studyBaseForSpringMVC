package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import java.sql.*;
import java.util.*;

public class JdbcMemberRepository implements  MemberRepository{
    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new  MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        //return  jdbcTemplate.quesry("select * from member where id=?",memberRowMapper);
        List<Member> result=jdbcTemplate.query("select * from member where id=?",memberRowMapper(),id);
        return result.stream().findAny();
    }
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result=jdbcTemplate.query("select * from member where name=?",memberRowMapper(),name);
        return result.stream().findAny();
    }
    @Override
    public List<Member> findAll() {
       return jdbcTemplate.query("select * from memeber ",memberRowMapper());
    }
    private RowMapper<Member> memberRowMapper(){
        return  new RowMapper<Member>(){
            @Override
            public Member mapRow(ResultSet rs,int rowNum) throws SQLException{
                Member member =new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
            }
        }
    }
}
