package pl.mdabkowski;

import java.util.List;

public class Cloud {
     private int cloudId;
     private int resourcesNumber;
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
        createPacketList();
    }

    private void createPacketList() {
        int numberOfPackets=0;
        for(int i =0;i<this.poisson.length;i++){
            if(this.poisson[i]!=0){
                int packetNumberInCurrentTime = this.poisson[i];
                for(int j=0;j<packetNumberInCurrentTime;j++){
                    Packet p = new Packet(cloudId,i,cloudId+i+j);
                    packetList.add(p);
                }
            }
        }
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
