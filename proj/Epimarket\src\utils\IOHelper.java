package utils;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

public class IOHelper
{

    public static byte[] getBytes(String fileName)
    {
        try {return getBytesFromFile(new File(fileName));}
        catch (Exception e) {System.out.println("\nNdWriter: getByteStream: " + e.toString()); return null;}
    }

    // Returns the contents of the file in a byte array.
    public static byte[] getBytesFromFile(File file) throws IOException
    {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type, It needs to be an int type.
        // Before converting to an int type, check to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE)
               System.out.println("NdWriter: getBytesFromFile: length > Integer.MAX_VALUE !");

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;

        while ((offset < bytes.length) && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0)
            offset += numRead;

        // Ensure all the bytes have been read in
        if (offset < bytes.length)
            throw new IOException("Could not completely read file " + file.getName());

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
}
