package com.mobeats.api;
import com.mobeats.api.service.ProductoDepositoService;
import com.mobeats.api.exception.ResourceNotFoundException;
import com.mobeats.api.model.ProductoDeposito;
import com.mobeats.api.repository.ProductoDepositoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductoDepositoServiceTest {

    @Mock
    private ProductoDepositoRepository productoDepositoRepositoryMock;

    @InjectMocks
    private ProductoDepositoService productoDepositoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProductosDepositos() {
        // Create a list of mock ProductoDepositos
        List<ProductoDeposito> mockProductosDepositos = new ArrayList<>();
        mockProductosDepositos.add(new ProductoDeposito());
        mockProductosDepositos.add(new ProductoDeposito());

        // Set up the behavior of the mock repository
        when(productoDepositoRepositoryMock.findAll()).thenReturn(mockProductosDepositos);

        // Perform the test
        List<ProductoDeposito> result = productoDepositoService.getAllProductosDepositos();

        // Verify the result
        assertEquals(mockProductosDepositos, result);
    }

    @Test
    public void testGetProductoDepositoById() {
        // Create a mock ProductoDeposito
        ProductoDeposito mockProductoDeposito = new ProductoDeposito();

        // Set up the behavior of the mock repository
        when(productoDepositoRepositoryMock.findById(1L)).thenReturn(Optional.of(mockProductoDeposito));

        // Perform the test
        ProductoDeposito result = productoDepositoService.getProductoDepositoById(1L);

        // Verify the result
        assertEquals(mockProductoDeposito, result);
    }

    @Test
    public void testGetProductoDepositoById_NotFound() {
        // Set up the behavior of the mock repository to return an empty Optional
        when(productoDepositoRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        // Perform the test and expect a ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> productoDepositoService.getProductoDepositoById(1L));
    }

    // Similarly, you can write tests for other methods like createProductoDeposito and updateProductoDeposito
    // Remember to mock the repository's behavior accordingly
}
