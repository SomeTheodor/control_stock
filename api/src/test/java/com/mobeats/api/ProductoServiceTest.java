package com.mobeats.api;

import com.mobeats.api.service.ProductoService;
import com.mobeats.api.exception.ResourceNotFoundException;
import com.mobeats.api.model.Producto;
import com.mobeats.api.repository.ProductoRepository;
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

public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepositoryMock;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProductos() {
        // Create a list of mock Productos
        List<Producto> mockProductos = new ArrayList<>();
        mockProductos.add(new Producto());
        mockProductos.add(new Producto());

        // Set up the behavior of the mock repository
        when(productoRepositoryMock.findAll()).thenReturn(mockProductos);

        // Perform the test
        List<Producto> result = productoService.getAllProductos();

        // Verify the result
        assertEquals(mockProductos, result);
    }

    @Test
    public void testGetProductoById() {
        // Create a mock Producto
        Producto mockProducto = new Producto();

        // Set up the behavior of the mock repository
        when(productoRepositoryMock.findById(1L)).thenReturn(Optional.of(mockProducto));

        // Perform the test
        Producto result = productoService.getProductoById(1L);

        // Verify the result
        assertEquals(mockProducto, result);
    }

    @Test
    public void testGetProductoById_NotFound() {
        // Set up the behavior of the mock repository to return an empty Optional
        when(productoRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        // Perform the test and expect a ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> productoService.getProductoById(1L));
    }
}
