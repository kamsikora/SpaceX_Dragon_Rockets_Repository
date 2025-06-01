package pl.kamsikora;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RocketTest {

    @Test
    void canCreateRocket() {
        Rocket rocket = new Rocket("Dragon");

        assertNotNull(rocket);
        assertEquals("Dragon", rocket.getName());
        assertEquals(RocketStatus.ON_GROUND, rocket.getStatus());
        assertFalse(rocket.isAssigned());
        assertNull(rocket.getMission());
    }

    @Test
    void canAssignRocketToMission() {
        Rocket rocket = new Rocket("Dragon");
        Mission mission = new Mission("Mars");

        rocket.assignToMission(mission);

        assertTrue(rocket.isAssigned());
        assertEquals(mission, rocket.getMission());
        assertEquals(RocketStatus.IN_SPACE, rocket.getStatus());
    }

    @Test
    void canUnassignRocketFromMission() {
        Rocket rocket = new Rocket("Dragon");
        Mission mission = new Mission("Mars");
        rocket.assignToMission(mission);

        rocket.unassignFromMission();

        assertFalse(rocket.isAssigned());
        assertNull(rocket.getMission());
        assertEquals(RocketStatus.ON_GROUND, rocket.getStatus());
    }

    @Test
    void canRepairRocket() {
        Rocket rocket = new Rocket("Dragon");
        rocket.repair();

        assertEquals(RocketStatus.IN_REPAIR, rocket.getStatus());
    }
}
