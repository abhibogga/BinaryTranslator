package COSMO_take_home_project;

public class packet {
    private String cosmoName;
    private String packetId; 
    private String packetSize;
    private int [] payloadArr; 
    private int passParity; 
    public packet(String cosmoName, String packetId, String packetSize, int [] payloadArr, int passParity) {
        this.cosmoName = cosmoName;
        this.packetId = packetId;
        this.packetSize = packetSize; 
        this.payloadArr = payloadArr;
        this.passParity = passParity;
    }
    
    private String makeArrString() {
        String returnString = "";
        for(int i = 0; i < payloadArr.length - 1 ; i++) {
            returnString += "Payload int " + i + ": " + payloadArr[i] + "\n";
        }

        returnString += "Parity Pass: " + ((passParity % 2 == 0) ? "true" : "false") + "\n";
        returnString += "Response: " + payloadArr[payloadArr.length -1];
        return returnString;
    }
    

    public String toString() {
        return "" + cosmoName + " " + packetId 
            + "\nPacket Size: " + packetSize +
            "\nPayload Size: " + ((payloadArr.length -1) *2) 
            + "\n" + makeArrString() + "\n";
    }

    

}