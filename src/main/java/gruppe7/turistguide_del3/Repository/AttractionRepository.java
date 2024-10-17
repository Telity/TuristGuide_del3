package gruppe7.turistguide_del3.Repository;

import gruppe7.turistguide_del3.Model.Attraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AttractionRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Attraction> getAllAttractions() {
        List<Attraction> attractions = new ArrayList<>();

        String query = "SELECT * FROM ATTRACTION";
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Attraction attraction = new Attraction();
                attraction.setId(resultSet.getInt("attraction_id"));
                attraction.setName(resultSet.getString("name"));
                attraction.setDescription(resultSet.getString("description"));
                attraction.setFee(resultSet.getInt("fee"));
                attraction.setCityId(resultSet.getInt("city_id"));
                attractions.add(attraction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attractions;
    }

    public List<String> getTagsList(){
        List<String> tags = new ArrayList<>();

        String query = "SELECT * FROM TAG";

        try(Connection con = DriverManager.getConnection(url, username, password)){
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                tags.add(resultSet.getString("tag_name"));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return tags;

    }

    public List<String> getTownList(){

        List<String> towns = new ArrayList<>();

        String query = "SELECT * FROM CITY";

        try(Connection con = DriverManager.getConnection(url, username, password)){
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                towns.add(resultSet.getString("city_name"));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return towns;

    }

    public List<Attraction> getAttractionByName(String name) {

        List<Attraction> attractions = new ArrayList<>();

        String query = "SELECT * FROM ATTRACTION WHERE name = ?";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Attraction attraction = new Attraction();
                attraction.setId(resultSet.getInt("attraction_id"));
                attraction.setName(resultSet.getString("name"));
                attraction.setDescription(resultSet.getString("description"));
                attraction.setFee(resultSet.getInt("fee"));
                attraction.setCityId(resultSet.getInt("city_id"));
                attractions.add(attraction);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return attractions;

    }

    public List<String> getTagsByName(String name) {
        List<String> tags = new ArrayList<>();

        String query = "SELECT T.name FROM TAG T " + "JOIN ATTRACTION_TAG AT ON T.tag_id = AT.tag_id " +
                "JOIN ATTRACTION A ON A.attraction_id = AT.attraction_id " + "WHERE A.name = ?";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tags.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tags;

    }


    public Attraction addAttraction(Attraction attraction) {

        String query = "INSERT INTO ATTRACTION (name, description, fee, city_id) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, attraction.getName());
            statement.setString(2, attraction.getDescription());
            statement.setInt(3, attraction.getFee());
            statement.setInt(4, attraction.getCityID());

            int updatedRows = statement.executeUpdate();

            if(updatedRows > 0){
                ResultSet result = statement.getGeneratedKeys(); // get the generated key
                if(result.next()){
                    int generatedId = result.getInt(1);
                    attraction.setId(generatedId);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return attraction;
    }

    public int deleteAttraction(String name) {
        int updatedRows = 0;

        String query = "DELETE FROM ATTRACTION WHERE name = ?";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1, name);

            updatedRows = statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return updatedRows;
    }


    public int updateAttraction(Attraction attraction) {

        int updatedRows = 0;

        String query = "UPDATE ATTRACTION SET name = ?, description = ?, fee = ?, city_id = ? WHERE attraction_id = ? ";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1, attraction.getName());
            statement.setString(2, attraction.getDescription());
            statement.setInt(3, attraction.getFee());
            statement.setInt(4, attraction.getCityID());
            statement.setInt(5, attraction.getId());

            updatedRows = statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return updatedRows;
    }
}

    /* public List<Attraction> addAttractionList(Attraction attraction) {
        attractions.add(attraction);
        return attractions;
    }
     */
