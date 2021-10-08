package ru.savinov.shop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.savinov.shop.dto.ProductDTO;
import ru.savinov.shop.test_helpers.factories.ProductDtoFactory;
import ru.savinov.shop.test_helpers.factories.ProductFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static ru.savinov.shop.test_helpers.TestConstant.ONE_PRODUCT;


@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    ProductService productService;

    CartService subject;

    @BeforeEach
    void setUp() {
        subject = new CartService(productService);
        subject.setProductsDTO(ProductDtoFactory.ofProductsDto());
    }
    @Test
    void testGetSumPrice() {
        int expected = subject.getSumPrice();
        assertEquals(expected, 60);
    }

    @Test
    void testAddProductById() {
        Long productId = 4L;
        when(productService.getProductById(productId)).thenReturn(ProductFactory.ofProduct());
        Integer countBeforeAdd = subject.getProductsDTO().size();
        subject.addProductById(productId);
        Integer countAfterAdd = subject.getProductsDTO().size();
        assertEquals(countBeforeAdd + ONE_PRODUCT, countAfterAdd);
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void testRemoveProductByNumberOnCart(int numberOnCart, String titleRemoved) {
        subject.removeProductByNumberOnCart(numberOnCart);
        boolean considered = subject.getProductsDTO().stream()
                .map(ProductDTO::getTitle)
                .anyMatch(title -> title.equals(titleRemoved));
        assertFalse(considered);
    }

    public static Stream<Arguments> getArguments() {
        return ProductDtoFactory.ofProductsDto().stream()
                .map(productDTO -> Arguments.of(productDTO.getNumberOnCart(), productDTO.getTitle()));
    }
}
