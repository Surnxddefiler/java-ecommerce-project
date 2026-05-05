package ecommerce.ecommerce_project.ProductClass;

public enum ProductOrderBy {
    NAME("name"),PRICE("price");
    private final String field;

    ProductOrderBy(String field) {
    this.field=field;
    }

    public String getField() {
        return field;
    }
}
