package pl.mdabkowski;

public class Packet {
    private int startTime;
    private boolean wasServed;
    private int delayTime;
    private int cloudId;
    private int packetId;

    public Packet(int cloudId, int startTime, int packetId) {
        this.cloudId = cloudId;
        this.startTime = startTime;
        this.packetId = packetId;
        wasServed = false;
    }

    public int getPacketId() {
        return packetId;
    }

    public void setPacketId(int packetId) {
        this.packetId = packetId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public boolean isWasServed() {
        return wasServed;
    }

    public void setWasServed(boolean wasServed) {
        this.wasServed = wasServed;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public int getCloudId() {
        return cloudId;
    }

    public void setCloudId(int cloudId) {
        this.cloudId = cloudId;
    }
}
