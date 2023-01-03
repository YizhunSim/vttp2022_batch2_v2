package sg.edu.nus.iss.vttp.workshop24.models;

public class LineItem {
    private String description;
    private Integer quantity;

    public LineItem(String description, Integer quantity){
        this.description = description;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
