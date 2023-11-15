package com.mobeats.api;

import com.mobeats.api.service.MovimientoService;
import com.mobeats.api.service.ProductoService;
import com.mobeats.api.service.ProductoDepositoService;
import com.mobeats.api.exception.ResourceNotFoundException;
import com.mobeats.api.model.Movimiento;
import com.mobeats.api.model.MovimientoTipo;
import com.mobeats.api.model.Producto;
import com.mobeats.api.model.ProductoDeposito;
import com.mobeats.api.repository.MovimientoRepository;
import com.mobeats.api.repository.MovimientoTipoRepository;
import com.mobeats.api.repository.ProductoDepositoRepository;
import com.mobeats.api.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MovimientoServiceTest {

    @Mock
    private MovimientoRepository movimientoRepositoryMock;

    @Mock
    private MovimientoTipoRepository movimientoTipoRepositoryMock;

    @Mock
    private ProductoDepositoRepository productoDepositoRepositoryMock;

    @Mock
    private ProductoRepository productoRepositoryMock;

    @Mock
    private ProductoDepositoService productoDepositoServiceMock;

    @Mock
    private ProductoService productoServiceMock;

    @InjectMocks
    private MovimientoService movimientoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMovimiento() {
        // Create a mock 
        Movimiento mockMovimiento = new Movimiento();

        // Set up the behavior of the mock repositories
        when(productoRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new Producto()));
        when(productoDepositoRepositoryMock.findByProductoAndDeposito(any(), any())).thenReturn(new ProductoDeposito());
        when(movimientoTipoRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new MovimientoTipo()));
        when(productoDepositoServiceMock.updateCantidadProductoDeposito(anyLong(), anyInt())).thenReturn(new ProductoDeposito());
        when(productoServiceMock.updateCantidadProducto(anyLong(), anyInt())).thenReturn(new Producto());
        when(movimientoRepositoryMock.save(any())).thenReturn(mockMovimiento);

        // Perform the test
        Movimiento result = movimientoService.createMovimiento(mockMovimiento);

        // Verify the result
        assertEquals(mockMovimiento, result);

        // Verify that the methods were called
        verify(productoDepositoServiceMock, times(1)).updateCantidadProductoDeposito(anyLong(), anyInt());
        verify(productoServiceMock, times(1)).updateCantidadProducto(anyLong(), anyInt());
        verify(movimientoRepositoryMock, times(1)).save(any());
    }

    // Add more tests for other methods as needed
}
