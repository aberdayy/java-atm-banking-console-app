public class OperationValidator {
    public static String validator(boolean operation){
        String successString =
                "################################################\n" +
                        "--------------Proccess Succesfull!--------------" +
                        "\n################################################";
        String failedString =
                "############################################\n" +
                        "--------------Proccess Failed!--------------" +
                        "\n############################################";

        if(operation!=false){
            return successString;
        }else{
            return failedString;
        }
    }
}
