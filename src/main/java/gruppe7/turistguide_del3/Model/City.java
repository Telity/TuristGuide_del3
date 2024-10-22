package gruppe7.turistguide_del3.Model;

public class City {

    private Integer city_id;
    private String city_name;

    public City(){
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    @Override
    public String toString() {
        return "City{id=" + city_id + ", name='" + city_name + "'}";
    }
}
