import java.util.ArrayList;

public class OrderListResponseJson {
    private ArrayList<DataForOrderCreationJson> orders;
    private PageInfoJson pageInfo;
    private ArrayList<AvailableStationsJson> availableStations;

    public ArrayList<DataForOrderCreationJson> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<DataForOrderCreationJson> orders) {
        this.orders = orders;
    }

    public PageInfoJson getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoJson pageInfo) {
        this.pageInfo = pageInfo;
    }

    public ArrayList<AvailableStationsJson> getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(ArrayList<AvailableStationsJson> availableStations) {
        this.availableStations = availableStations;
    }
}
