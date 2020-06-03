package pl.mdabkowski;

import java.util.List;

public class Federation {
    private List<Cloud> federation;
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

}
