package be.multimedi.springAdvanced;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository("beerDao")
public class BeerDaoImpl implements BeerDao {
    private final static String QUERY_ID = "select Name, Alcohol, Price, Stock from Beers where Id=?";
    private final static String QUERY_ALCOHOL = "select Name, Alcohol, Price, Stock from Beers where Alcohol = ?";
    private final static String UPDATE_STOCK = "update Beers set Stock = ? where Id = ?";

    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public String getBeerById(int id) {
        Map<String, Object> queryResult = template.queryForMap(QUERY_ID, id);

        return beerInformationToString(queryResult);
    }

    private String beerInformationToString(Map<String, Object> beer) {
        return String.format(
                "%s %s %s %s",
                beer.get("Name"),
                beer.get("Alcohol"),
                beer.get("Price"),
                beer.get("Stock")
        );
    }

    @Override
    public void setStock(int id, int stock) {
        template.update(UPDATE_STOCK, stock, id);
    }

    @Override
    public List<String> getBeerByAlcohol(float alcohol) {
        List<Map<String, Object>> beersByAlcohol = template.queryForList(QUERY_ALCOHOL, alcohol);

        return beersByAlcohol
                .stream()
                .map(this::beerInformationToString)
                .collect(Collectors.toList());
    }
}
