package com.mobeats.api;

import com.mobeats.api.service.MovimientoTipoService;
import com.mobeats.api.exception.ResourceNotFoundException;
import com.mobeats.api.model.MovimientoTipo;
import com.mobeats.api.repository.MovimientoTipoRepository;
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

public class MovimientoTipoServiceTest {

    @Mock
    private MovimientoTipoRepository movimientoTipoRepositoryMock;

    @InjectMocks
    private MovimientoTipoService movimientoTipoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllMovimientoTipos() {
        // Create a list of mock MovimientoTipos
        List<MovimientoTipo> mockMovimientoTipos = new ArrayList<>();
        mockMovimientoTipos.add(new MovimientoTipo());
        mockMovimientoTipos.add(new MovimientoTipo());

        // Set up the behavior of the mock repository
        when(movimientoTipoRepositoryMock.findAll()).thenReturn(mockMovimientoTipos);

        // Perform the test
        List<MovimientoTipo> result = movimientoTipoService.getAllMovimientoTipos();

        // Verify the result
        assertEquals(mockMovimientoTipos, result);
    }

    @Test
    public void testGetMovimientoTipoById() {
        // Create a mock MovimientoTipo
        MovimientoTipo mockMovimientoTipo = new MovimientoTipo();

        // Set up the behavior of the mock repository
        when(movimientoTipoRepositoryMock.findById(1L)).thenReturn(Optional.of(mockMovimientoTipo));

        // Perform the test
        MovimientoTipo result = movimientoTipoService.getMovimientoTipoById(1L);

        // Verify the result
        assertEquals(mockMovimientoTipo, result);
    }

    @Test
    public void testGetMovimientoTipoById_NotFound() {
        // Set up the behavior of the mock repository to return an empty Optional
        when(movimientoTipoRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        // Perform the test and expect a ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> movimientoTipoService.getMovimientoTipoById(1L));
    }

    // Similarly, you can write tests for other methods like createMovimientoTipo and updateMovimientoTipo
    // Remember to mock the repository's behavior accordingly
}
