package org.springframework.samples.petclinic.vet.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.vet.model.Vet;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VetServiceTests {

    @Autowired
    VetService service;

    @Test
    void shouldFindVets() {
        Collection<Vet> vets = service.allVets();

        assertThat(vets)
                .filteredOn(vet -> vet.getId() == 3)
                .hasSize(1)
                .first()
                .hasFieldOrPropertyWithValue("lastName", "Douglas")
                .hasFieldOrPropertyWithValue("nrOfSpecialties", 2)
                .extracting(Vet::getSpecialties).asList()
                .extracting("name")
                .containsExactly("dentistry", "surgery");
    }

}