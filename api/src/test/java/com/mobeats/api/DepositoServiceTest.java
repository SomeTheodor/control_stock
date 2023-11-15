package com.mobeats.api;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.mobeats.api.service.DepositoService;
import com.mobeats.api.exception.ResourceNotFoundException;
import com.mobeats.api.model.Deposito;
import com.mobeats.api.repository.DepositoRepository;

public class DepositoServiceTest {

    @Mock
    private DepositoRepository depositoRepositoryMock;

    @InjectMocks
    private DepositoService depositoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllDepositos() {
        // Create a list of mock Depositos
        List<Deposito> mockDepositos = new ArrayList<>();
        mockDepositos.add(new Deposito(1L, "Deposito 1"));
        mockDepositos.add(new Deposito(2L, "Deposito 2"));

        // Set up the behavior of the mock repository
        when(depositoRepositoryMock.findAll()).thenReturn(mockDepositos);

        // Perform the test
        List<Deposito> result = depositoService.getAllDepositos();

        // Verify the result
        assertEquals(mockDepositos, result);
    }

    @Test
    public void testGetDepositoById() {
        // Create a mock Deposito
        Deposito mockDeposito = new Deposito(1L, "Deposito 1");

        // Set up the behavior of the mock repository
        when(depositoRepositoryMock.findById(1L)).thenReturn(Optional.of(mockDeposito));

        // Perform the test
        Deposito result = depositoService.getDepositoById(1L);

        // Verify the result
        assertEquals(mockDeposito, result);
    }

    @Test
    public void testGetDepositoById_NotFound() {
        // Set up the behavior of the mock repository to return an empty Optional
        when(depositoRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        // Perform the test and expect a ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> depositoService.getDepositoById(1L));
    }

}

