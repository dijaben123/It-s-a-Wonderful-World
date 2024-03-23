package iww;

import java.io.OutputStream;

/**
 * This class represents an OutputStream that discards all data written to it.
 */
public class NullOutputStream extends OutputStream {
    

    /**
     * Constructs a new instance of NullOutputStream.
     * This OutputStream implementation does nothing when the write() method is called.
     */
    public NullOutputStream(){}
    
    /**
     * Writes the specified byte to this output stream. Since this is a null
     * output stream, it does nothing.
     *
     * @param b The byte to write.
     */
    public void write(int b) {
        // Ne rien faire
    }
    
}
