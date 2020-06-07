package pl.mdabkowski;

import java.util.List;

import static pl.mdabkowski.Main.TIME_SIZE;

public class Federation {
    private int commonPoolResources;
    private List<Cloud> cloudFederationList;
    boolean debug = true;
    void addNewCloud(Cloud c){
        cloudFederationList.add(c);
    }
    void setCommonPoolResources(int cpResources){
        this.commonPoolResources = cpResources;
    }
    void setup(){

    }
    void simulate(){
        List<Packet> packetsInput = null;
        for(int i =0;i<cloudFederationList.size();i++){
            for(int j = 0;j<cloudFederationList.get(i).getPacketList().size();j++){
                packetsInput.add(cloudFederationList.get(i).getPacketList().get(j));
            }
        }
        if(debug){for(int i = 0;i<packetsInput.size();i++){
            System.out.println("Packet from cloud: "+packetsInput.get(i).getCloudId()+" was started in time: "+ packetsInput.get(i).getStartTime());
        }}
        int[] firstCategoryResources = new int[cloudFederationList.size()];
        int[] secondCategoryResources = new int[cloudFederationList.size()];
        for(int i = 0; i< cloudFederationList.size();i++){
            firstCategoryResources[i] = cloudFederationList.get(i).getFirstCategoryResourcesNumber();
            secondCategoryResources[i] = cloudFederationList.get(i).getSecondCategoryResourceNumber();
        }

        for(int i = 0;i<TIME_SIZE;i++){
            List<Packet> currentTimePackets = null;
            int currentCommonPoolResources = commonPoolResources;
            for(int k = 0; k< cloudFederationList.size();k++){
                firstCategoryResources[k] = cloudFederationList.get(k).getFirstCategoryResourcesNumber();
                secondCategoryResources[k] = cloudFederationList.get(k).getSecondCategoryResourceNumber();
            }
            /* calculating currentTimePackets */
            for(int j=0;j<packetsInput.size();j++){
                if(packetsInput.get(j).getStartTime()==i){
                    currentTimePackets.add(packetsInput.get(j));
                }
            }

            for(int j=0;j<currentTimePackets.size();j++){
                int idCloud = currentTimePackets.get(j).getCloudId();
                if(firstCategoryResources[i]>0){
                    firstCategoryResources[i]--;
                    currentTimePackets.get(j).setWasServed(true);
                }else if(currentCommonPoolResources>0){
                    currentTimePackets.get(j).setWasServed(true);
                    currentCommonPoolResources--;
                }else if(secondCategoryResources[i]>0){
                    currentTimePackets.get(j).setWasServed(true);
                    secondCategoryResources[i]--;
                }else{
                    currentTimePackets.get(j).setWasServed(false);
                }
            }


        }

    }

    /*private List<Cloud> federation;
    private double publicResourcesNumber;
    private double temp;
    public void addFederation(Cloud newCloud){
        federation.add(newCloud);
    }
    public Federation(double temp){
        this.temp = temp;
    }
    private int findSmallestResourceNumber(){
        int result = federation.get(0).getResourcesNumber();
        for(int i = 0;i<federation.size();i++){
            if(result>federation.get(i).getResourcesNumber()){
                result=federation.get(i).getResourcesNumber();
            }
        }
        return result;
    }

    private void setPublicResourcesNumber(){
        int smallest = findSmallestResourceNumber();
        publicResourcesNumber = temp*smallest;
    }
    private int setCategory1PrivateResourcesNumber(){
        int result=0;
        return result;
    }
    private int setCategory2PrivateResourcesNumber(){
        int result=0;
        return result;
    }
    public void calculate(){
        for(int i=0;i<federation.size();i++){

        }
    }
 */
}
