package storage;

import product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private final Map<String, List<Product>> storage = new HashMap<>();

    public void addProducts(Product... product) {
        /* TODO: 상품을 추가하는 메서드 구현
         * - 등록되지 않은 상품의 경우, 새롭게 등록한 후 재고를 추가
         * - 이미 등록된 상품의 경우, 재고만 추가
         * - 재량에 따라 추가적인 기능 구현
         */
    }

    public List<Product> getProduct(String name, int number) {
        /* TODO: 상품을 꺼내오는 메서드 구현
         * - 등록되지 않은 상품의 경우, 실패 처리
         * - 등록된 상품의 경우, 재고를 꺼내와서 반환
         * - 재량에 따라 추가적인 기능 구현
         */
    }

    public void printProductsStock() {
        /* TODO: 상품 재고를 출력하는 메서드 구현
         * - 상품마다 몇 개의 재고가 있는지 출력
         */
    }
}