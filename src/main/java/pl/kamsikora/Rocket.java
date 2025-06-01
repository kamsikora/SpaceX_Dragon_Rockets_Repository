package pl.kamsikora;

class Rocket implements Assignable {
    private final String name;
    private boolean isAssigned;
    private Mission mission;
    private RocketStatus status = RocketStatus.ON_GROUND;

    Rocket(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    boolean isAssigned() {
        return isAssigned;
    }

    Mission getMission() {
        return mission;
    }

    RocketStatus getStatus() {
        return status;
    }

    public void assignToMission(Mission mission) {
    }

    public void unassignFromMission() {
    }

    void repair() {
    }
}