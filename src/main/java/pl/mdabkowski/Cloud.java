package pl.mdabkowski;

import java.util.ArrayList;
import java.util.List;

import static pl.mdabkowski.Constants.TIME_SIZE;

public class Cloud {
     private int cloudId;
     private int resourcesNumber;

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

    public Cloud(int cloudId, int resourcesNumber, int timeSize, double lambda) {
        this.cloudId = cloudId;
        this.resourcesNumber = resourcesNumber;
        this.poisson = Poisson.calculate(timeSize,lambda);
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
                    System.out.println("resources"+resources);
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
        System.out.println("Cloud: "+cloudId+" packets number: "+(notServedPackets+servedPackets)+" served packets: "+servedPackets+" not served Packets: "+notServedPackets);
    }
    private List<Packet> createPacketList() {
        int numberOfPackets=0;
        List<Packet> packetList2= new ArrayList<Packet>();
        for(int i = 0; i<this.poisson.length; i++){
            if(this.poisson[i]!=0){
                int packetNumberInCurrentTime = this.poisson[i];
                for(int j=0;j<packetNumberInCurrentTime;j++){
                    Packet p = new Packet(cloudId,i,cloudId+i+j);
                    System.out.println("|"+p.getStartTime()+p.getCloudId()+p.getPacketId() +"|");

                    packetList2.add(p);
                }
            }
        }
        return packetList2;
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
/*private int resourcesNumber;
    private int resourcesNumberPrivate;
    private int resourcesNumberPublic;
    private int timeSize;
    private double lambda;
    private double[] poisson;
    private double[] result;
    private double[] notOperatedArray;
    private double[] operatedArray;

    public int getResourcesNumber() {
        return resourcesNumber;
    }

    public void setResourcesNumber(int resourcesNumber) {
        this.resourcesNumber = resourcesNumber;
    }

    public int getTimeSize() {
        return timeSize;
    }

    public void setTimeSize(int timeSize) {
        this.timeSize = timeSize;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public double[] getPoisson() {
        return poisson;
    }

    public void setPoisson(double[] poisson) {
        this.poisson = poisson;
    }

    public Cloud(int resourcesNumber, int timeSize, double lambda){
        this.lambda=lambda;
        this.resourcesNumber=resourcesNumber;
        this.timeSize=timeSize;
        this.poisson = Poisson.calculate(timeSize,lambda);
        notOperatedArray = new double[timeSize];
        operatedArray = new double[timeSize];
    }
    public void serviceCheck() {
        int operated = 0;
        int notOperated = 0;

        for (int i = 0; i < timeSize; i++) {
            if (resourcesNumber >= (int) poisson[i]) {
                operated += (int) poisson[i];
                operatedArray[i] = (int) poisson[i];
                notOperatedArray[i] = 0;
            } else {
                operated += resourcesNumber;
                notOperated += ((int) poisson[i] - resourcesNumber);
                operatedArray[i] = resourcesNumber;
                notOperatedArray[i] = ((int) poisson[i] - resourcesNumber);
            }
            System.out.println("for i number: "+ i+ "cloud operated: "+operated+"cloud have not operated: "+notOperated);
        }
    }
    public void showResult(){
        for (int i = 0;i < timeSize;i++){
            System.out.println("for i number: "+ i+ "cloud operated: "+operatedArray[i]+"cloud have not operated: "+notOperatedArray[i]);
        }

    }
*/
}
