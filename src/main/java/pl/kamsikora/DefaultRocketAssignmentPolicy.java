package pl.kamsikora;

class DefaultRocketAssignmentPolicy implements RocketAssignmentPolicy {
    @Override
    public boolean canAssign(Rocket rocket) {
        return !rocket.isAssigned();
    }

    @Override
    public void assign(Rocket rocket, Mission mission) {
        rocket.assignToMission(mission);
    }

    @Override
    public void unassign(Rocket rocket) {
        rocket.unassignFromMission();
    }

    @Override
    public void repair(Rocket rocket) {
        rocket.repair();
    }
}