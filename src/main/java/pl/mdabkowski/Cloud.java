package pl.mdabkowski;

public class Cloud {
    private int resourcesNumber;
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

}
