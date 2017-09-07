package org.grpcsvrest.rcs.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContentServiceTest {

    private final ContentService service = new ContentService("pokemons.txt");

    @Test
    public void testGetByIndex() {
        assertThat(service.getByIndex(0))
                .isEqualTo(new ContentDto(0, "bulbasaur", true));
        assertThat(service.getByIndex(846))
                .isEqualTo(new ContentDto(846, "marshadow", false));
    }

}