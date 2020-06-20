package pl.mdabkowski;


public class Poisson {
    public static int[] calculate(int k, double lambda,int multiplier){
        int[] result = new int[k];
        for(int i =0;i<k;i++){
            if(poisson(i, lambda)*Constants.constant*multiplier<1) {
                result[i]=0;

            }else{
                result[i] =(int)( poisson(i, lambda)*Constants.constant*multiplier);
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