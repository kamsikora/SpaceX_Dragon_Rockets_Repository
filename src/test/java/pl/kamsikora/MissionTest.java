package pl.kamsikora;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MissionTest {

    @Test
    void canCreateMission() {
        Mission mission = new Mission("Mars");

        assertNotNull(mission);
        assertEquals("Mars", mission.getName());
        assertEquals(MissionStatus.SCHEDULED, mission.getStatus());
    }

    @Test
    void canAssignAndUnassignRocket() {
        Mission mission = new Mission("Mars");
        Rocket rocket = new Rocket("Dragon");

        mission.assignRocket(rocket, MissionStatus.IN_PROGRESS);

        assertTrue(mission.getRockets().contains(rocket));
        assertEquals(MissionStatus.IN_PROGRESS, mission.getStatus());

        mission.unassignRocket(rocket);

        assertFalse(mission.getRockets().contains(rocket));
        assertEquals(MissionStatus.ENDED, mission.getStatus());
    }

    @Test
    void canSetMissionToPendingByPauseMission() {
        Mission mission = new Mission("Mars");
        mission.pauseMission();

        assertEquals(MissionStatus.PENDING, mission.getStatus());
    }

    @Test
    void canHandleMultipleRockets() {
        Mission mission = new Mission("Mars");
        Rocket rocket1 = new Rocket("Dragon 1");
        Rocket rocket2 = new Rocket("Dragon 2");

        mission.assignRocket(rocket1, MissionStatus.IN_PROGRESS);
        mission.assignRocket(rocket2, MissionStatus.IN_PROGRESS);

        assertTrue(mission.getRockets().contains(rocket1));
        assertTrue(mission.getRockets().contains(rocket2));
        assertEquals(MissionStatus.IN_PROGRESS, mission.getStatus());

        mission.unassignRocket(rocket1);
        assertFalse(mission.getRockets().contains(rocket1));
        assertTrue(mission.getRockets().contains(rocket2));
    }
}
