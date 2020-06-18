package pl.mdabkowski;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private boolean isOn = true;
    int cloudNumber=0;
    List<Cloud> cloudList = new ArrayList<Cloud>();
    Federation f1 = new Federation();
    public UI(){
        this.isOn=true;
    }

    public void start(){
        System.out.println("Welcome in federation simulation app");
        while(isOn){
            showMenu();
            Scanner scan = new Scanner(System.in);
            int option = scan.nextInt();
            if(option==1){
                addNewCloud();
            }else if(option==2){
                SCSimulation();
            }else if(option==3){
                createFederation();
            }else if(option==4){
                simulateFederation();
            }else if(option==5){
                showPacketInput();
            }else if(option==6){
                isOn=false;
            }
        }
    }

    private void showPacketInput() {
        System.out.println("Packet input for each cloud: ");
        for(int i=0;i<cloudList.size();i++){
            System.out.println("\nFor cloud "+cloudList.get(i).getCloudId());
            for(int j=0;j<cloudList.get(i).getPoisson().length;j++){
                System.out.print("|"+cloudList.get(i).getPoisson()[j]+"|");
            }
        }
        System.out.println("");
    }

    private void simulateFederation() {
        System.out.println("Simulating cloud federation\nPlease choose method of creating common Pool:\n1. FC method\n2. PFC\n3. Create common pool as a highest possible resource number");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        if(option==1){
            f1.setup("FC");
        }else if(option==2){
            System.out.println("Currently not working, under development ;(");
        }else if(option==3){
            f1.setup("najwiekszaMozliwaWartosc");
        }else{
            System.out.println("Wrong option");
        }
    }

    private void createFederation() {
        System.out.println("Creating federation of clouds");
        for(int i=0;i<cloudList.size();i++){
            f1.addNewCloud(cloudList.get(i));
        }
        System.out.println("Federation is build");
    }

    private void showMenu(){
        System.out.println("Choose options: \n1. Add new cloud\n2. Create simulation of clouds(SC)\n3. Create federation consisting all created clouds\n4. Simulate Federation\n5. Show packet input of each Cloud \n6. Quit application");
    }
    private void addNewCloud(){
        int lambda;
        int resourcesNumber;
        System.out.println("Creating new Cloud!\nPlease provide number of resources of this cloud: ");
        Scanner scan = new Scanner(System.in);
        resourcesNumber = scan.nextInt();
        System.out.println("Please provide poisson distribution number: \n");
        lambda = scan.nextInt();
        cloudList.add(new Cloud(cloudNumber,resourcesNumber,Constants.TIME_SIZE,lambda));
        cloudNumber++;
    }
    private void SCSimulation(){
        if(cloudList.size()>0) {
            for (int i = 0; i < cloudList.size(); i++) {
                cloudList.get(i).simulate();
            }
            for (int i = 0; i < cloudList.size(); i++) {
                cloudList.get(i).showResult();
            }
        }else{
            System.out.println("Unfortunetly there are no clouds added, please add cloud before doing simulation");
        }
    }
}
