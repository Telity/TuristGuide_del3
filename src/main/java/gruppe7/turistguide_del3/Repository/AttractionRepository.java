package gruppe7.turistguide_del3.Repository;

import gruppe7.turistguide_del3.Model.Attraction;
import gruppe7.turistguide_del3.Model.City;
import gruppe7.turistguide_del3.Model.Tag;
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
                attraction.setCityID(resultSet.getInt("city_id"));
                attraction.setTown(getTownByCityId(attraction.getCityID()));
                attractions.add(attraction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attractions;
    }

    public List<Tag> getTagsList() {
        List<Tag> tags = new ArrayList<>();

        String query = "SELECT * FROM TAG";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Tag tag = new Tag();
                tag.setTag_id(resultSet.getInt("tag_id"));
                tag.setTag_name(resultSet.getString("tag_name"));
                tags.add(tag);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return tags;
    }

    public List<City> getTownList() {

        List<City> towns = new ArrayList<>();

        String query = "SELECT * FROM CITY";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                City city = new City();
                city.setCity_id(resultSet.getInt("city_id"));
                city.setCity_name(resultSet.getString("city_name"));
                towns.add(city);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return towns;
    }

    public Attraction getAttractionByName(String name) {

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
                attraction.setCityID(resultSet.getInt("city_id"));
                attraction.setTown(getTownByCityId(attraction.getCityID()));
                return attraction;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private String getTownByCityId(int cityId) {

        String town = null;

        String query = "SELECT * FROM CITY WHERE city_id = ?";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, cityId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                town = resultSet.getString("city_name");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return town;

    }

    public List<String> getTagsByName(String name) {
        List<String> tags = new ArrayList<>();

        String query = "SELECT T.tag_name FROM TAG T " + "JOIN ATTRACTION_TAG AT ON T.tag_id = AT.tag_id " +
                "JOIN ATTRACTION A ON A.attraction_id = AT.attraction_id " + "WHERE A.name = ?";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tags.add(resultSet.getString("tag_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tags;

    }


    public void addAttraction(Attraction attraction, List<Tag> tags) {

        String query = "INSERT INTO ATTRACTION (name, description, fee, city_id) VALUES (?, ?, ?, ?)";
        String queryTags = "INSERT INTO ATTRACTION_TAG (attraction_id, tag_id) VALUES (?, ?)";

        try(Connection con = DriverManager.getConnection(url, username, password)) {

            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, attraction.getName());
                statement.setString(2, attraction.getDescription());
                statement.setInt(3, attraction.getFee());

                // Hvis cityID er null, sætter vi værdien til NULL i databasen i stedet for at få en fejl.
                // Dette kunne også gøres i databasen ved at skrive NOT NULL ud fra city_id
                if (attraction.getCityID() != null) {
                    statement.setInt(4, attraction.getCityID());
                } else {
                    statement.setNull(4, java.sql.Types.INTEGER); // Sæt som NULL hvis cityID er null
                }

                int updatedRows = statement.executeUpdate();

                if (updatedRows > 0) {
                    ResultSet result = statement.getGeneratedKeys(); // get the generated key
                    if (result.next()) {
                        int generatedId = result.getInt(1);
                        attraction.setId(generatedId);
                    }
                }

                try (PreparedStatement tagStatement = con.prepareStatement(queryTags)) {
                    for (Tag tag : tags) {
                        tagStatement.setInt(1, attraction.getId());
                        tagStatement.setInt(2, tag.getTag_id());
                        tagStatement.addBatch();
                    }
                    tagStatement.executeBatch();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

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


    public int updateAttraction(Attraction attraction, List<Tag> tags) {

        int updatedRows = 0;

        String query = "UPDATE ATTRACTION SET name = ?, description = ?, fee = ?, city_id = ? WHERE attraction_id = ? ";
        String deleteTags = "DELETE FROM ATTRACTION_TAG WHERE attraction_id = ?";
        String addNewTags = "INSERT INTO ATTRACTION_TAG (attraction_id, tag_id) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1, attraction.getName());
            statement.setString(2, attraction.getDescription());
            statement.setInt(3, attraction.getFee());

            if (attraction.getCityID() != null) {
                statement.setInt(4, attraction.getCityID());
            } else {
                statement.setNull(4, java.sql.Types.INTEGER); // Sæt som NULL hvis cityID er null
            }
            statement.setInt(5, attraction.getId());

            updatedRows = statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement deletestatement = con.prepareStatement(deleteTags);

            deletestatement.setInt(1, attraction.getId());

            deletestatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try(Connection con = DriverManager.getConnection(url, username, password)){
            PreparedStatement updateStatement = con.prepareStatement(addNewTags);
            for(Tag tag : tags) {
                updateStatement.setInt(1, attraction.getId());
                updateStatement.setInt(2, tag.getTag_id());
                updateStatement.addBatch();
            }
            updateStatement.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return updatedRows;
    }
}

