package pl.mdabkowski;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private boolean isOn = true;
    int cloudNumber=0;
    List<Cloud> cloudList = new ArrayList<Cloud>();
    FileWriter writer;
    Federation f1 = new Federation();
    public UI(){
        this.isOn=true;

    }

    public void start() throws IOException {
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
                showResults();
            }else if(option==7){
                showAllresults();
            }else if(option==0){
                isOn=false;
            }
        }
    }

    private void showAllresults() {
        System.out.println("SC: ");
        SCSimulation();
        showResults();
        System.out.println("FC: ");
        simulateFederation(1);
        showResults();
        System.out.println("PFC: ");
        simulateFederation(2);
        showResults();



    }

    private void showResults() {
        for (int i = 0; i < cloudList.size(); i++) {
            cloudList.get(i).showResult();

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
            f1.simulate();
        }else if(option==2){
            f1.setup("PFC");
            f1.simulate();
        }else if(option==3){
            f1.setup("najwiekszaMozliwaWartosc");
            f1.simulate();
        }else{
            System.out.println("Wrong option");
        }
    }
    private void simulateFederation(int option) {
        if(option==1){
            f1.setup("FC");
            f1.simulate();
        }else if(option==2){
            f1.setup("PFC");
            f1.simulate();
        }else if(option==3){
            f1.setup("najwiekszaMozliwaWartosc");
            f1.simulate();
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
        System.out.println("Choose options: \n1. Add new cloud\n2. Create simulation of clouds(SC)\n3. Create federation consisting all created clouds\n4. Set Federation common pool method\n5. Show packet input of each Cloud \n6. Simulate according to current setup\n7. Make all possible simulation and save result to .csv file\n0. Quit application");
    }
    private void addNewCloud(){
        int lambda,multiplier;
        int resourcesNumber;
        System.out.println("Creating new Cloud!\nPlease provide number of resources of this cloud: ");
        Scanner scan = new Scanner(System.in);
        resourcesNumber = scan.nextInt();
        System.out.println("Please provide poisson distribution number: \n");
        lambda = scan.nextInt();
        System.out.println("Please provide resources ratio rate: \n");
        multiplier = scan.nextInt();

        cloudList.add(new Cloud(cloudNumber,resourcesNumber,Constants.TIME_SIZE,lambda,multiplier));
        cloudNumber++;
    }
    private void SCSimulation(){
        if(cloudList.size()>0) {
            for (int i = 0; i < cloudList.size(); i++) {
                cloudList.get(i).simulate();
            }
        }else{
            System.out.println("Unfortunetly there are no clouds added, please add cloud before doing simulation");
        }
    }
}
