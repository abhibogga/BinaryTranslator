package COSMO_take_home_project;

import java.util.ArrayList;

public class dataHandler {
    //Instance variables
    private String cosmoName = "COSMO SSRL";
    private String packetId;
    private String payloadBinary;
    private int payloadSize;
    private String payload; 
    private String parityBit;
    public boolean needResPackage;
    


    
    //Enter what instance variables we have now, don't worry about further ones, 
    //ADD FURTHER INSTANCE VARIABLES AS WE GO, MAKE SURE TO DO THIS
    public dataHandler(String cosmoName, String packetId, String payloadBinary, int payloadSize, String payload, String parityBit ) {
        this.packetId = packetId;
        this.payloadBinary = payloadBinary;
        this.payloadSize = payloadSize;
        this.payload = payload;
        this.parityBit = parityBit;


    }
        
    public String everything = getNameInBinary() + getPacketId() + getPayloadBinary() + getParityBit() + getPayload();

    public String getName()
    {
        return cosmoName;
    }

    public int getPayloadSize() {
        return payload.length();
    }

    public String getPayload() {
        return payload;
    }
    public String getEverything() {
        return everything;
    }

    public String getPacketId() {
        return packetId;
    }

    public String getParityBit() {
        return parityBit;
    }

    public String getNameInBinary() {
        return "01000011011011110111001101101101011011110010000001010011010100110101001001001100";
    }

    public String getPayloadBinary() {
        return payloadBinary;
    }

    public String getTranslatedPacketId() {
        return Integer.toString(processBinaryString(packetId));
    }
    // IF YOU HAVE TIME MAKE THIS A MORE EXCITING ALGORITHM 
    public int returnEvens(String binary) {
        int counter = 0; 
        for(int i = 0; i < binary.length(); i++) {
            if(binary.substring(i, i+1).equals("1")) {
                counter++;
            }
        }
        return counter;
    }

    public int getPayloadSize(String binary) {
        return processBinaryString(binary);
    }
    

    public static int processBinaryString(String binary) {
        int returnNum = 0;
        int num = 0;
        for (int i = binary.length() -1; i >= 0; i--) {
            returnNum += (Math.pow(2, num) * Integer.parseInt(String.valueOf(binary.charAt(i))));
            num += 1;
        }
        return returnNum;
    }


    //Make an arraylist to process the amount of bytes
    //Keep in mind that each int is 2 bytes long, so make sure that you divide the payloadAmt by 2
    //
    public int [] enterNums(int[] arr, String binaryString) {
        int sum = 0; 
        for(int i = 0; i < payload.length(); i += 16){
            arr[i/16] = processBinaryString(binaryString.substring(i, i+16));
            sum += processBinaryString(binaryString.substring(i, i+16));
        }
        arr[arr.length - 1] = sum;
        return arr; 

    }

    public String toString() {
        String returnString = "";
        //returnString += this.cosmoName + processBinaryString(this.packetId) + "\n" + "Packet Size: " + returnEvensAndPayloadSize(payload) + "\nPayload Size" + payloadSize;
        returnString = Integer.toString(payload.length());
        return returnString;
    }
   
    
}

