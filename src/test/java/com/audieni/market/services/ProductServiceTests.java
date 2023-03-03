package com.audieni.market.services;

import com.audieni.market.models.Product;
import com.audieni.market.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTests {
    public ProductService sut;

    @Mock
    private Product mockProduct;

    @Mock
    private List<Product> mockProductList;

    @Mock
    ProductRepository mockProductRepository;

    @Test
    void findAllTest() {
        sut = new ProductService(mockProductRepository);
        Mockito.when(mockProductRepository.findAll()).thenReturn(mockProductList);
        Optional<List<Product>> products = Optional.of(sut.findAll());
        Assertions.assertEquals(Optional.of(mockProductList), products);
    }

    @Test
    void findByIdTest() {
        sut = new ProductService(mockProductRepository);
        int id = 1;
        Mockito.when(mockProductRepository.findById(id)).thenReturn(mockProduct);
        Optional<Product> product = Optional.ofNullable(sut.findById(id));
        Assertions.assertEquals(Optional.of(mockProduct), product);
    }

    @Test
    void saveTest() {
        sut = new ProductService(mockProductRepository);
        Mockito.when(mockProductRepository.save(mockProduct)).thenReturn(mockProduct);
        Optional<Product> product = Optional.of(sut.save(mockProduct));
        Assertions.assertEquals(Optional.of(mockProduct), product);
    }

    @Test
    void saveAllTest() {
        sut = new ProductService(mockProductRepository);
        Mockito.when(mockProductRepository.saveAll(mockProductList)).thenReturn(mockProductList);
        Optional<List<Product>> products = Optional.of(sut.saveAll(mockProductList));
        Assertions.assertEquals(Optional.of(mockProductList), products);
    }
}
