package pl.mdabkowski;

import java.util.ArrayList;
import java.util.List;

import static pl.mdabkowski.Constants.TIME_SIZE;


public class Federation {
    private int commonPoolResources;

    public List<Cloud> getCloudFederationList() {
        return cloudFederationList;
    }

    public void setCloudFederationList(List<Cloud> cloudFederationList) {
        this.cloudFederationList = cloudFederationList;
    }

    private List<Cloud> cloudFederationList;
    boolean debug = false;
    public Federation(){
        this.cloudFederationList = new ArrayList<Cloud>();

    }
    void addNewCloud(Cloud c){
        cloudFederationList.add(c);
    }
    void setCommonPoolResources(int cpResources){
        this.commonPoolResources = cpResources;
    }
    void setup(String option){
        if(option == "najwiekszaMozliwaWartosc"){
            int result=cloudFederationList.get(0).getResourcesNumber();
            for(int i = 0; i< cloudFederationList.size();i++){
                if(result>cloudFederationList.get(i).getResourcesNumber()){
                    result  =cloudFederationList.get(i).getResourcesNumber();
                }
            }
            setCommonPoolResources(result*cloudFederationList.size());
            for(int i=0; i< cloudFederationList.size();i++){
                int privateResources = cloudFederationList.get(i).getResourcesNumber()-commonPoolResources;

                cloudFederationList.get(i).setFirstCategoryResourcesNumber(privateResources);
                cloudFederationList.get(i).setSecondCategoryResourceNumber(0);
            }
        }else if(option == "FC"){
            int result=0;
            for(int i =0;i<cloudFederationList.size();i++){
                result+=cloudFederationList.get(i).getResourcesNumber();
                cloudFederationList.get(i).setFirstCategoryResourcesNumber(0);
                cloudFederationList.get(i).setSecondCategoryResourceNumber(0);
            }

        }else if(option == "PFC"){
            int lowestRequestRate=cloudFederationList.get(0).getMultiplier();
            int lowestResourcesNumber=cloudFederationList.get(0).getResourcesNumber();
            int averageRequestRate=0;
            for(int i = 0;i<cloudFederationList.size();i++){
                if(cloudFederationList.get(i).getMultiplier()<lowestRequestRate){
                    lowestRequestRate = cloudFederationList.get(i).getMultiplier();
                }
                if(cloudFederationList.get(i).getResourcesNumber()<lowestResourcesNumber){
                    lowestResourcesNumber = cloudFederationList.get(i).getResourcesNumber();
                }
                averageRequestRate+=cloudFederationList.get(i).getMultiplier();
            }
            averageRequestRate = averageRequestRate/cloudFederationList.size();
            System.out.println("asdasd"+averageRequestRate+" "+lowestRequestRate+" "+lowestResourcesNumber);
            if(lowestRequestRate>lowestResourcesNumber){
                setCommonPoolResources(lowestResourcesNumber*cloudFederationList.size());

                for(int i=0; i< cloudFederationList.size();i++){
                    int privateResources = cloudFederationList.get(i).getResourcesNumber()-(commonPoolResources/cloudFederationList.size());
                    System.out.println(privateResources+" "+cloudFederationList.get(i).getMultiplier()+" "+averageRequestRate);
                    int tempSecound = privateResources/((cloudFederationList.get(i).getMultiplier()/averageRequestRate)+1);
                    int tempFirst = privateResources-tempSecound;
                    cloudFederationList.get(i).setFirstCategoryResourcesNumber(tempFirst);
                    cloudFederationList.get(i).setSecondCategoryResourceNumber(tempSecound);
                    System.out.println("KUTAS:"+i+" "+tempSecound+" "+tempFirst);
                }
            }else{
                setCommonPoolResources(lowestRequestRate*cloudFederationList.size());
                for(int i=0; i< cloudFederationList.size();i++){
                    int privateResources = cloudFederationList.get(i).getResourcesNumber()-(commonPoolResources/cloudFederationList.size());
                    System.out.println(privateResources+" "+cloudFederationList.get(i).getMultiplier()+" "+averageRequestRate);
                    int tempSecound = privateResources/((cloudFederationList.get(i).getMultiplier()/averageRequestRate)+1);
                    int tempFirst = privateResources-tempSecound;
                    System.out.println("KUTAS:"+i+" "+tempSecound+" "+tempFirst);
                    cloudFederationList.get(i).setFirstCategoryResourcesNumber(tempFirst);
                    cloudFederationList.get(i).setSecondCategoryResourceNumber(tempSecound);
                }
                System.out.println("KUTAS:"+commonPoolResources);
            }
        }

    }
    void setup(String option, double value){

    }

    void simulate(){
        List<Packet> packetsInput = new ArrayList<Packet>();
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
       /* for(int i=0; i<packetsInput.size();i++){
            System.out.println("Packet"+ packetsInput.get(i).getPacketId()+" of cloud "+packetsInput.get(i).getCloudId()+" Of start time"+packetsInput.get(i).getStartTime());
        }*/

        for(int i=0;i < TIME_SIZE;i++){

            //calculate first category and second category resources in current Time for each cloud
            List<Packet> currentTimePackets = new ArrayList<Packet>();
            int currentCommonPoolResources = commonPoolResources;
            if(debug)System.out.println("cp"+ currentCommonPoolResources);
            for(int k = 0; k< cloudFederationList.size();k++){
                firstCategoryResources[k] = cloudFederationList.get(k).getFirstCategoryResourcesNumber();
                secondCategoryResources[k] = cloudFederationList.get(k).getSecondCategoryResourceNumber();
            }
            //create Packet list in current time
            for(int j=0;j<packetsInput.size();j++){
                if(packetsInput.get(j).getStartTime()==i){
                    currentTimePackets.add(packetsInput.get(j));
                    //System.out.println("Pakiet z chmury: "+packetsInput.get(j).getCloudId()+" zaczynajacy sie w czasie"+packetsInput.get(j).getStartTime());
                }
            }

            for(int j=0;j<currentTimePackets.size();j++){
                int cloudId = currentTimePackets.get(j).getCloudId();
                if(firstCategoryResources[cloudId]>0){
                    currentTimePackets.get(j).setWasServed(true);
                    firstCategoryResources[cloudId]--;
                }else if(currentCommonPoolResources>0){
                    currentTimePackets.get(j).setWasServed(true);
                    currentCommonPoolResources--;
                }else if(secondCategoryResources[cloudId]>0){
                    currentTimePackets.get(j).setWasServed(true);
                    secondCategoryResources[cloudId]--;
                }else{
                    currentTimePackets.get(j).setWasServed(false);
                }

            }


            if(debug)System.out.print(currentCommonPoolResources+" ");

        }



        }

    }

