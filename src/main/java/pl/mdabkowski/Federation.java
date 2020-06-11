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
    boolean debug = true;
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
            setCommonPoolResources(result);
            for(int i=0; i< cloudFederationList.size();i++){
                int privateResources = cloudFederationList.get(i).getResourcesNumber()-commonPoolResources;

                cloudFederationList.get(i).setFirstCategoryResourcesNumber(privateResources);
                cloudFederationList.get(i).setSecondCategoryResourceNumber(0);
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
            System.out.println("cp"+ currentCommonPoolResources);
            for(int k = 0; k< cloudFederationList.size();k++){
                firstCategoryResources[k] = cloudFederationList.get(k).getFirstCategoryResourcesNumber();
                secondCategoryResources[k] = cloudFederationList.get(k).getSecondCategoryResourceNumber();
            }
            //create Packet list in current time
            for(int j=0;j<packetsInput.size();j++){
                if(packetsInput.get(j).getStartTime()==i){
                    currentTimePackets.add(packetsInput.get(j));
                }
            }
            //if there are packets in current time calculate
            for(int j=0;j<2;j++) {
               // System.out.println("For i" + i + "for j " + j);
                if (currentTimePackets.isEmpty() != true) {
                    int idCloud = currentTimePackets.get(j).getCloudId();
                    if (firstCategoryResources[j] > 0) {
                        firstCategoryResources[j]--;
                        currentTimePackets.get(j).setWasServed(true);
                        System.out.println("was served by first");
                    } else if (currentCommonPoolResources > 0) {
                        currentTimePackets.get(j).setWasServed(true);
                        currentCommonPoolResources--;
                        System.out.println("was served by cp");
                    } else if (secondCategoryResources[i] > 0) {
                        currentTimePackets.get(j).setWasServed(true);
                        secondCategoryResources[j]--;
                        System.out.println("was served by second");
                    } else {
                        currentTimePackets.get(j).setWasServed(false);
                    }
                }
            }

        }




        /*for(int i = 0;i<Constants.TIME_SIZE;i++){
            List<Packet> currentTimePackets = new ArrayList<Packet>();
            int currentCommonPoolResources = commonPoolResources;
            for(int k = 0; k< cloudFederationList.size();k++){
                firstCategoryResources[k] = cloudFederationList.get(k).getFirstCategoryResourcesNumber();
                secondCategoryResources[k] = cloudFederationList.get(k).getSecondCategoryResourceNumber();
            }
            /* calculating currentTimePackets *//*
            for(int j=0;j<packetsInput.size();j++){
                if(packetsInput.get(j).getStartTime()==i){
                    currentTimePackets.add(packetsInput.get(j));
                }
            }
            System.out.println("|"+firstCategoryResources+"|");
            for(int k=0;k<firstCategoryResources.length;k++){
                System.out.println("|"+firstCategoryResources[k]+"|");
                System.out.println("|"+secondCategoryResources[k]+"|");
            }

            for(int j=0;j<2;j++){
                System.out.println("For i"+i+"for j "+ j);
                int idCloud = currentTimePackets.get(j).getCloudId();
                if(firstCategoryResources[j]>0){
                    firstCategoryResources[j]--;
                    currentTimePackets.get(j).setWasServed(true);
                }else if(currentCommonPoolResources>0){
                    currentTimePackets.get(j).setWasServed(true);
                    currentCommonPoolResources--;
                }else if(secondCategoryResources[i]>0){
                    currentTimePackets.get(j).setWasServed(true);
                    secondCategoryResources[j]--;
                }else{
                    currentTimePackets.get(j).setWasServed(false);
                }
            }*/


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

}*/
