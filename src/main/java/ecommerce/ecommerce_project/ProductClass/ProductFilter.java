package ecommerce.ecommerce_project.ProductClass;

public record ProductFilter(
        Double startPrice,
        Double endPrice,
        Integer currentPage, Integer pageSize, ProductOrderBy productOrderBy
) {
}
