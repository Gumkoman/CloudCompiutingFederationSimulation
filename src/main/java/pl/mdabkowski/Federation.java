package pl.mdabkowski;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static pl.mdabkowski.Constants.TIME_SIZE;


public class Federation {
    public int getCommonPoolResources() {
        return commonPoolResources;
    }

    private int commonPoolResources;
    private String option;
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
        this.option = option;
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
            int[] resourcesRatio = new int[cloudFederationList.size()];
            int[] resources = new int[cloudFederationList.size()];
            for(int i =0;i<cloudFederationList.size();i++){
                resourcesRatio[i] = cloudFederationList.get(i).getMultiplier();
                resources[i] = cloudFederationList.get(i).getResourcesNumber();
            }
            int sum1 = IntStream.of(resourcesRatio).sum();
            int sum2 = IntStream.of(resources).sum();
            double result1 = sum2/cloudFederationList.size();
            double[] cost = new double[cloudFederationList.size()];
            if(debug)System.out.println("sum1 "+sum1);
            if(debug)System.out.println("sum2 "+ sum2);
            if(debug)System.out.println("result1 "+result1);
            for(int i =0;i<cloudFederationList.size();i++){
                cost[i] = resources[i]-result1;
                if(debug)System.out.println("TEST");
                if(debug)System.out.println("RR "+resourcesRatio[i]);
                if(debug)System.out.println("R "+ resources[i]);
            }
            for(int i =0;i<cloudFederationList.size();i++){
                if(debug)System.out.println("Cost: "+cost[i]+" for clod: "+i);
                cloudFederationList.get(i).setCostOfFcFederation(cost[i]);
            }
            setCommonPoolResources(result);

        }else if(option == "PFC"){
            int lowestRequestRate=cloudFederationList.get(0).getMultiplier();
            int lowestResourcesNumber=cloudFederationList.get(0).getResourcesNumber();
            int averageRequestRate=0;
            for(int i = 0;i<cloudFederationList.size();i++){
                cloudFederationList.get(i).setCostOfFcFederation(0);
                if(cloudFederationList.get(i).getMultiplier()<lowestRequestRate){
                    lowestRequestRate = cloudFederationList.get(i).getMultiplier();
                }
                if(cloudFederationList.get(i).getResourcesNumber()<lowestResourcesNumber){
                    lowestResourcesNumber = cloudFederationList.get(i).getResourcesNumber();
                }
                averageRequestRate+=cloudFederationList.get(i).getMultiplier();
            }
            averageRequestRate = averageRequestRate/cloudFederationList.size();
            if(debug)System.out.println("asdasd"+averageRequestRate+" "+lowestRequestRate+" "+lowestResourcesNumber);
            if(lowestRequestRate>lowestResourcesNumber){
                setCommonPoolResources(lowestResourcesNumber*cloudFederationList.size());

                for(int i=0; i< cloudFederationList.size();i++){
                    int privateResources = cloudFederationList.get(i).getResourcesNumber()-(commonPoolResources/cloudFederationList.size());
                    if(debug)System.out.println(privateResources+" "+cloudFederationList.get(i).getMultiplier()+" "+averageRequestRate);
                    int tempSecound = privateResources/((cloudFederationList.get(i).getMultiplier()/averageRequestRate)+1);
                    int tempFirst = privateResources-tempSecound;
                    cloudFederationList.get(i).setFirstCategoryResourcesNumber(tempFirst);
                    cloudFederationList.get(i).setSecondCategoryResourceNumber(tempSecound);
                    if(debug)System.out.println("KUTAS:"+i+" "+tempSecound+" "+tempFirst);
                }
            }else{
                setCommonPoolResources(lowestRequestRate*cloudFederationList.size());
                for(int i=0; i< cloudFederationList.size();i++){
                    int privateResources = cloudFederationList.get(i).getResourcesNumber()-(commonPoolResources/cloudFederationList.size());
                    if(debug)System.out.println(privateResources+" "+cloudFederationList.get(i).getMultiplier()+" "+averageRequestRate);
                    int tempSecound = privateResources/((cloudFederationList.get(i).getMultiplier()/averageRequestRate)+1);
                    int tempFirst = privateResources-tempSecound;
                    if(debug)System.out.println("Test:"+i+" "+tempSecound+" "+tempFirst);
                    cloudFederationList.get(i).setFirstCategoryResourcesNumber(tempFirst);
                    cloudFederationList.get(i).setSecondCategoryResourceNumber(tempSecound);
                }
                if(debug)System.out.println("KUTAS:"+commonPoolResources);
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
            if(debug)System.out.println("Packet from cloud: "+packetsInput.get(i).getCloudId()+" was started in time: "+ packetsInput.get(i).getStartTime());
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

            /*for(int j=0;j<currentTimePackets.size();j++){
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

            }*/
            int[] currentNeed = new int[cloudFederationList.size()];
            for(int j=0;j<currentTimePackets.size();j++){
                //int[] currentNeed = new int[cloudFederationList.size()];
                int cloudId = currentTimePackets.get(j).getCloudId();
                if(firstCategoryResources[cloudId]>0){
                    currentTimePackets.get(j).setWasServed(true);
                    firstCategoryResources[cloudId]--;
                }else if(currentCommonPoolResources>0){
                    //if(option=="PFC") {
                        currentNeed[currentTimePackets.get(j).getCloudId()] += 1;
                    //}else if(option == "FC"){
                        //currentTimePackets.get(j).setWasServed(true);
                        //currentCommonPoolResources--;
                    //}
                }else if(secondCategoryResources[cloudId]>0){
                    currentTimePackets.get(j).setWasServed(true);
                    secondCategoryResources[cloudId]--;
                }else{
                    currentTimePackets.get(j).setWasServed(false);
                }

            }
            if(option=="PFC"||option=="FC"){
                int currentNeedFromAllClouds=0;
                for (int k=0;k<currentNeed.length;k++){
                    currentNeedFromAllClouds+=currentNeed[k];
                }
                if(currentNeedFromAllClouds<commonPoolResources){
                   // System.out.println("less : ");
                    for(int k =0;k<currentNeed.length;k++){
                        for(int g=0;g<cloudFederationList.get(k).getPacketList().size();g++){
                            //System.out.println("Dla czasu i: "+i+" pakiet o czasie : "+cloudFederationList.get(k).getPacketList().get(g).getStartTime()+" czy byl juz obsluzony ? : " +cloudFederationList.get(k).getPacketList().get(g).isWasServed());
                            if(cloudFederationList.get(k).getPacketList().get(g).isWasServed()==false && cloudFederationList.get(k).getPacketList().get(g).getStartTime()== i){
                                cloudFederationList.get(k).getPacketList().get(g).setWasServed(true);
                                if(debug)System.out.println("Obsluzono przy urzyciu cp, dla chmury bez dzielenia zasobow: "+k+" w czasie "+ i);
                            }
                        }
                    }
                }else{

                    int sum = IntStream.of(currentNeed).sum();
                    int[] currentCpForCloud=new int[cloudFederationList.size()];
                    for(int k=0;k<currentCpForCloud.length;k++){
                        currentCpForCloud[k] = (int) (commonPoolResources*currentNeed[k]/sum);
                        if(debug)System.out.print("loop" );
                        if(debug)System.out.print(currentCpForCloud[k]);
                        if(debug)System.out.println();
                    }
                    for(int k=0;k<currentCpForCloud.length;k++){
                        for(int g=0;g<cloudFederationList.get(k).getPacketList().size();g++){
                            if(cloudFederationList.get(k).getPacketList().get(g).isWasServed()==false && currentCpForCloud[k]>0){
                                cloudFederationList.get(k).getPacketList().get(g).setWasServed(true);
                                currentCpForCloud[k]--;
                               // System.out.println("Obsluzono przy urzyciu cp, dla chmury: "+k+" w czasie "+ i);
                            }
                        }
                    }

                }
            }else if(option=="FC"){

            }
            if(debug)System.out.print(currentCommonPoolResources+" ");

        }



        }

    }

