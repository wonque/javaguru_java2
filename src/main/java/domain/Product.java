package domain;

import services.ProductPriceService;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private String title;
    private BigDecimal price;
    private String description;
    private ProductPriceService priceService;

    public Product (){
        this.priceService = new ProductPriceService();
        this.price = BigDecimal.valueOf(0);
    }

    public void setPrice(double price) {
        this.price = priceService.returnValidatedPrice(price);
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        return price.compareTo(other.price) == 0 &&
                Objects.equals(title, other.title) &&
                Objects.equals(description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
