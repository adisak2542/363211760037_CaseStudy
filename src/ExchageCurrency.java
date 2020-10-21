public class ExchageCurrency {
    public static void main(String[] args) {

        ExChangeAPI ex = new ExChangeAPI();
        //getconnection with "USD"
        if (ex.getConnection("USD")){
            System.out.println(ex.getResult());
        }
        System.out.println(ex.getEschRate());
        System.out.println(ex.getEschRate("THB"));







    }
}
