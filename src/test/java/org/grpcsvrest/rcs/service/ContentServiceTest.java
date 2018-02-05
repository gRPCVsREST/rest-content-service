package org.grpcsvrest.rcs.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContentServiceTest {

    private final ContentService service = new ContentService("pokemons.txt");

    @Test
    public void testGetByIndex() {
        assertThat(service.getByIndex(1).hasNext()).isTrue();
        assertThat(service.getByIndex(23).hasNext()).isFalse();
    }

}