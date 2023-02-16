package COSMO_take_home_project;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class runner {
    // Parse through what we have in input file
    public static void main(String[] args) throws Exception {
        // Put all of text file into on big string
        File file = new File("C:\\Users\\asb52284\\VSC_Workspace\\COSMO_take_home_project\\input.txt");

        FileWriter myWriter = new FileWriter("returnPackets.txt");

        try {
            File returnPacket = new File("returnPackets.txt");
            if (returnPacket.createNewFile()) {
                System.out.println("File created: " + returnPacket.getName());
            }
        } catch (IOException ioe) {
            System.err.println("An error occurred.");
        }

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int i = 0;
        int counter = 0;
        String cosmoName = "";
        String packetId = "";
        String payloadBinary = "";
        int payloadSize = 0;
        String payload = "";
        String parityBit = "";
        dataHandler dh;
        returnPacket rp;
        String everything;
        int[] payloadArr;
        int round = 0;
        ArrayList<packet> arr = new ArrayList<packet>();
        while ((i = br.read()) != -1) {

            if (counter >= 94 + (payloadSize * 8)) {
                dh = new dataHandler(cosmoName, packetId, payloadBinary, payloadSize, payload, parityBit);
                // Instatiate Everything variable
                everything = dh.getNameInBinary() + dh.getPacketId() + dh.getPayloadBinary() + dh.getParityBit()
                        + dh.getPayload();
                // If dh passes parity bit test then call the return packet
                if (dh.returnEvens(everything) % 2 == 0) {
                    // Call the returnPacket class here
                    rp = new returnPacket(cosmoName, dh.getPacketId(), dh.getPayloadBinary(), dh.getPayload());
                }

                // Create payload array
                payloadArr = new int[(dh.getPayloadSize() / 16) + 1];
                // Call packetClass
                arr.add(new packet(dh.getName(), dh.getTranslatedPacketId(),
                        Integer.toString(dh.returnEvens(payload)),
                        dh.enterNums(payloadArr, dh.getPayload()), dh.returnEvens(everything)));
                // Make sure to reset everything
                counter = 0;
                cosmoName = "";
                packetId = "";
                payloadBinary = "";
                payloadSize = 0;
                payload = "";
                parityBit = "";
                round++;
            }

            if (counter < 80) {
                cosmoName += (char) i;
            } else if (counter >= 80 && counter < 85) {
                packetId += (char) i;
            } else if (counter >= 85 && counter < 93) {
                payloadBinary += (char) i; // This is the binary representation of how many bytes are in the payload
            }

            if (counter >= 93) {
                // Now we know to use static method
                payloadSize = dataHandler.processBinaryString(payloadBinary);
            }

            if (counter >= 93 && counter < 94) {
                parityBit += (char) i;
            }

            if (counter >= 94 && counter < 94 + (payloadSize * 8)) {
                payload += (char) i;
            }
            // Calculate payload size
            counter++;

        }

        // Make sure to catch the last instace of this data handler

        dh = new dataHandler(cosmoName, packetId, payloadBinary, payloadSize, payload, parityBit);
        // Instatiate Everything variable
        everything = dh.getNameInBinary() + dh.getPacketId() + dh.getPayloadBinary() + dh.getParityBit()
                + dh.getPayload();
        // If dh passes parity bit test then call the return packet
        if (dh.returnEvens(everything) % 2 == 0) {
            // Call the returnPacket class here
            rp = new returnPacket(cosmoName, dh.getPacketId(), dh.getPayloadBinary(), dh.getPayload());
        }

        // Create payload array
        payloadArr = new int[(dh.getPayloadSize() / 16) + 1];
        // Call packetClass
        arr.add(new packet(dh.getName(), dh.getTranslatedPacketId(),
                Integer.toString(dh.returnEvens(payload)),
                dh.enterNums(payloadArr, dh.getPayload()), dh.returnEvens(everything)));
        for (int k = 0; k < arr.size(); k++) {
            System.out.println(arr.get(k).toString());
        }

        // Return packet printer
        try {

            myWriter.write(returnPacket.returnString);
            myWriter.close();
        } catch (IOException ioe) {
            System.err.println("An error occurred.");
        }

    }
}
