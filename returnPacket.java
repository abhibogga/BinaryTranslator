package COSMO_take_home_project;
public class returnPacket {
    public static String returnString = "";
    public returnPacket(String cosmoName, String packetId, String payloadBinary, String payload) {
        returnString += "0100001101101111011100110110110101101111001000000101001101010011010100100100110000100000010100100100010101010011" + packetId + payloadBinary + payload + " ";
        
}

    
}
