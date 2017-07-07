/**
 * Created by elischwat on 7/6/17.
 */

package Utilities;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ByteOrder;


public final class JavaUtilities {

    public static int[] intArrayFromByteArray(byte[] input){
        IntBuffer intBuf = ByteBuffer.wrap(input).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
        int[] output = new int[intBuf.capacity()];
        intBuf.get(output);
        return output;
    }

}
