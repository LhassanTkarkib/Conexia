package com.simplon.GroupeService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class GroupRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GroupRepository groupRepository;

    private Group group;

    @BeforeEach
    void setUp() {
        group = new Group();
        group.setIdAdmin(1L);
        group.setGroupName("Test Group");
        group.setGroupDescription("This is a test group");

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void whenFindById_thenReturnGroup() {
        Optional<Group> found = groupRepository.findById(group.getIdGroup());
        assertTrue(found.isPresent());
        assertEquals(group.getIdGroup(), found.get().getIdGroup());
    }

    @Test
    void whenFindAll_thenReturnAllGroups() {
        List<Group> groups = groupRepository.findAll();
        assertEquals(1, groups.size());
    }

    @Test
    void whenDeleteById_thenDeletingShouldBeSuccessful() {
        groupRepository.deleteById(group.getIdGroup());
        Optional<Group> notFound = groupRepository.findById(group.getIdGroup());
        assertTrue(notFound.isEmpty());
    }
}