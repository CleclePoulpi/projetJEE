package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import projectJEE.Backend.entities.Location;
import projectJEE.Backend.entities.discipline;
import projectJEE.Backend.service.statService;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
@Component
public class statServiceImpl implements statService, CommandLineRunner {

    @Autowired
    private JdbcTemplate jbdcTemplate;
    @Override
    public List<Location> getMostPopularLocations() {
        String sql = "SELECT site.id,location_name,location_city,location_category " +
                "FROM site JOIN event ON site.id = event.location_id " +
                "GROUP BY id,location_name,location_city,location_category ORDER BY COUNT(*) DESC LIMIT 5";
        List<Location> locations = jbdcTemplate.query(sql, (rs, rowNum) ->
                new Location(rs.getString("location_name"),
                rs.getString("location_city"), rs.getString("location_category")));
        return locations;
    }

    @Override
    public List<discipline> getMostPopularDisciplines() {
        String sql = "SELECT discipline.id,discipline.name,discipline.paralympic,event.start_time, event.end_time " +
                "FROM discipline JOIN event ON discipline.id = event.discipline_id " +
                "GROUP BY discipline.id,discipline.name,discipline.paralympic,event.start_time, event.end_time ";
        List<discipline> disciplines = jbdcTemplate.query(sql, (rs, rowNum) ->
                new discipline(rs.getString("discipline_name"),rs.getBoolean("paralympic")));
        System.out.println(disciplines);
        return disciplines;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
