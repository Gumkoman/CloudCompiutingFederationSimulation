package pl.mdabkowski;

import java.util.ArrayList;
import java.util.List;

import static pl.mdabkowski.Constants.TIME_SIZE;

public class Cloud {
     private int cloudId;
     private int resourcesNumber;



    private int multiplier;
    public int[] getPoisson() {
        return poisson;
    }

    public void setPoisson(int[] poisson) {
        this.poisson = poisson;
    }

    private int[] poisson;
     private int firstCategoryResourcesNumber;
     private int secondCategoryResourceNumber;
     private List<Packet> packetList;

    public List<Packet> getPacketList() {
        return packetList;
    }

    public void setPacketList(List<Packet> packetList) {
        this.packetList = packetList;
    }

    public Cloud(int cloudId, int resourcesNumber, int timeSize, double lambda, int multiplier) {
        this.cloudId = cloudId;
        this.resourcesNumber = resourcesNumber;
        this.multiplier = multiplier;
        this.poisson = Poisson.calculate(timeSize,lambda,multiplier);
        this.packetList = createPacketList();
    }
    public void simulate(){
        for(int i = 0;i<TIME_SIZE;i++){
            List<Packet> currentTimePackets = new ArrayList<Packet>();
            for(int j=0;j<packetList.size();j++){
                if(packetList.get(j).getStartTime()==i){
                    currentTimePackets.add(packetList.get(j));
                }
            }
            int resources = resourcesNumber;
            for(int j=0;j<currentTimePackets.size();j++){
                if(resources>0){
                    currentTimePackets.get(j).setWasServed(true);
                    resources--;
                }else{
                    currentTimePackets.get(j).setWasServed(false);
                }
            }
        }
    }

    public void showResult(){
        int servedPackets=0;
        int notServedPackets=0;

        for(int i =0;i<packetList.size();i++){
            if(packetList.get(i).isWasServed()){
                servedPackets++;
            }else{
                notServedPackets++;
            }

        }
        System.out.println("Cloud: "+cloudId+" with "+resourcesNumber+" own resources "+" packets number: "+(notServedPackets+servedPackets)+" served packets: "+servedPackets+" not served Packets: "+notServedPackets);
    }
    private List<Packet> createPacketList() {
        int numberOfPackets=0;
        List<Packet> packetList2= new ArrayList<Packet>();
        for(int i = 0; i<this.poisson.length; i++){
            if(this.poisson[i]!=0){
                int packetNumberInCurrentTime = this.poisson[i];
                for(int j=0;j<packetNumberInCurrentTime;j++){
                    Packet p = new Packet(cloudId,i,cloudId+i+j);
                    packetList2.add(p);
                }
            }
        }
        return packetList2;
    }
    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
    public int getCloudId() {
        return cloudId;
    }

    public void setCloudId(int cloudId) {
        this.cloudId = cloudId;
    }

    public int getResourcesNumber() {
        return resourcesNumber;
    }

    public void setResourcesNumber(int resourcesNumber) {
        this.resourcesNumber = resourcesNumber;
    }

    public int getFirstCategoryResourcesNumber() {
        return firstCategoryResourcesNumber;
    }

    public void setFirstCategoryResourcesNumber(int firstCategoryResourcesNumber) {
        this.firstCategoryResourcesNumber = firstCategoryResourcesNumber;
    }

    public int getSecondCategoryResourceNumber() {
        return secondCategoryResourceNumber;
    }

    public void setSecondCategoryResourceNumber(int secondCategoryResourceNumber) {
        this.secondCategoryResourceNumber = secondCategoryResourceNumber;
    }

}
