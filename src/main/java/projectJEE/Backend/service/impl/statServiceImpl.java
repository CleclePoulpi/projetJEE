package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import projectJEE.Backend.entities.Location;
import projectJEE.Backend.entities.discipline;
import projectJEE.Backend.entities.timecalc;
import projectJEE.Backend.service.statService;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        System.out.println(locations);
        return locations;
    }

    @Override
    public List<discipline> getMostPopularDisciplines() {
        String sql = "SELECT discipline.id,discipline.name,discipline.paralympic,event.start_time, event.end_time " +
                "FROM discipline JOIN event ON discipline.id = event.discipline_id " +
                "GROUP BY discipline.id,discipline.name,discipline.paralympic,event.start_time, event.end_time ";

        List<timecalc> disciplines = jbdcTemplate.query(sql, (rs, rowNum) -> {
            String time1 = rs.getTime("start_time").toString();
            String time2 = rs.getTime("end_time").toString();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = format.parse(time1);
                date2 = format.parse(time2);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            long time = date2.getTime() - date1.getTime();
            return new timecalc(time, new discipline(rs.getInt("id"),rs.getString("name"), rs.getBoolean("paralympic")));
        });

        List<timecalc> bestTime = new ArrayList<>();
        bestTime.add(disciplines.get(0));
        for(int i = 1; i < disciplines.size(); i++){
            Boolean found = false;
            int indexi = 0;
            int indexj = 0;
            for(int j = 0; j < bestTime.size(); j++){
                if(bestTime.get(j).getdiscipline().equals(disciplines.get(i).getdiscipline())){
                    found = true;
                    indexi = i;
                    indexj = j;
                }
            }
            if(found){
                bestTime.get(indexj).updatetime(disciplines.get(indexi).gettime());
            }else {
                bestTime.add(disciplines.get(i));
            }
        }
        bestTime.sort((o1, o2) -> (int) (o2.gettime() - o1.gettime()));

        ArrayList<discipline> result = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            result.add(bestTime.get(i).getdiscipline());
        }
        System.out.println(result);
        return result;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
