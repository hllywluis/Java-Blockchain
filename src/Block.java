import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private String hash;
    private String lastHash;
    private String data;
    private long timeStamp;
    private int nonce;

    /**
     * The constructor for the Block class. This constructor requires each variable declared in the Block class to be
     * initially declared.
     * @param hash The initial value for the hash of the new Block.
     * @param lastHash The initial value for the previous hash of the new Block.
     * @param data The initial value for the data of the new Block.
     * @param timeStamp The initial value for the timestamp of the new Block.
     * @param nonce The initial value for the nonce of the new Block.
     */
    public Block(String hash, String lastHash, String data, long timeStamp, int nonce) {
        this.hash = hash;
        this.lastHash = lastHash;
        this.data = data;
        this.timeStamp = timeStamp;
        this.nonce = nonce;
    }

    /**
     * mineBlock mines a new block in the blockchain. The way that the block is "mined" is by simply making the function
     * calculate a relatively complex mathematical problem. As soon as the result is found, the while loop is exited and
     * the new hash value is returned.
     *
     * @param prefix The prefix that is desired to be found.
     * @return The calculated hash. The mineBlock function uses the prefix to calculate the hash.
     */
    public String mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while(!hash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            hash = calculateBlockHash();
        }
        return hash;
    }

    /**
     * This function calculates a block hash. First, we concatenate a few parts of the block to generate a string that
     * we can generate a hash from. Next, we get an instance of the SHA-256 hash function from MessageDigest. After this,
     * we generate the hash value of the input data, which is stored here as a byte array. Finally, the byte array is
     * transformed into a hex string, since a hash is typically represented as a 32-digit hex number.
     *
     * @return The calculated block hash as a hex number. The for loop handles the transition from a byte array to a hex
     * number.
     */
    public String calculateBlockHash() {
        String dataToHash = lastHash + Long.toString(timeStamp) + Integer.toString(nonce) + data;
        MessageDigest digest = null;
        byte[] bytes = null;

        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
        } catch(NoSuchAlgorithmException exception) {
            System.err.println(exception.getMessage());
        }
        StringBuilder hexNumber = new StringBuilder();

        assert bytes != null;
        for (byte bt : bytes) {
            hexNumber.append(String.format("%02x", bt));
        }

        return hexNumber.toString();
    }

    /**
     * getHash returns the value of the private "hash" variable.
     * @return The value of the "hash" variable.
     */
    public String getHash() {
        return hash;
    }

    /**
     * getLastHash returns the value of the private "lastHash" variable.
     * @return The value of the "lastHash" variable.
     */
    public String getLastHash() {
        return lastHash;
    }

    /**
     * getData returns the value of the private "data" variable.
     * @return The value of the "data" variable.
     */
    public String getData() {
        return data;
    }

    /**
     * getTimeStamp returns the value of the private "timeStamp" variable.
     * @return The value of the "timeStamp" variable.
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * getNonce returns the value of the private "nonce" variable.
     * @return The value of the "nonce" variable.
     */
    public int getNonce() {
        return nonce;
    }

    /**
     * setHash allows mutation of the private "hash" variable.
     * @param hash The new value that "hash" should take.
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * setLastHash allows mutation of the private "lastHash" variable.
     * @param lastHash The new value that "lastHash" should take.
     */
    public void setLastHash(String lastHash) {
        this.lastHash = lastHash;
    }

    /**
     * setData allows mutation of the private "data" variable.
     * @param data The new value that "data" should take.
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * setTimeStamp allows mutation of the private "timeStamp" variable.
     * @param timeStamp The new value that "timeStamp" should take.
     */
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * setNonce allows mutation of the private "nonce" variable.
     * @param nonce The new value that "nonce" should take.
     */
    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
}
