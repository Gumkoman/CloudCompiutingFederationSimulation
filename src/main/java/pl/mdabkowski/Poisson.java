package pl.mdabkowski;


public class Poisson {
    public static double[] calculate(int k, double lambda){
        double[] result = new double[k];
        for(int i =0;i<k;i++){
            if(poisson(i, lambda)*100<1) {
                result[i]=0;

            }else{
                result[i] = poisson(i, lambda)*100;
            }
        }
        return result;
    }
    private static double poisson(int k, double lambda){
        return (Math.pow(lambda, k) / Math.pow(Math.E,lambda)) / factorial(k);
    }
    private static long factorial(int number) {
        long result = 1;
        for(int i=1;i<=number;i++){
            result = result*i;
        }
        return result;
    }
}