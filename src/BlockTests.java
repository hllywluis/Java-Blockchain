import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlockTests {
    List<Block> blockchain = new ArrayList<>();
    int prefix = 4;
    String prefixString = new String(new char[prefix]).replace('\0', '0');

    /**
     * Test whether we can add a block to the blockchain. The block is mined according to the prefix value set. Therefore,
     * a larger prefix would mean a more difficult block to mine. Additionally, the test checks whether the hash of the new
     * block is equal to the prefix's string.
     */
    @Test
    public void givenBlockchain_whenNewBlockAdded_thenSuccess() {
        String data;

        if (blockchain.size() <= 0) {
            data = "This block is the initial block, therefore it has no initial data.";
        } else {
            data = blockchain.get(blockchain.size() - 1).getHash();
        }

        Block newBlock = new Block("This is a new block.", data, new Date().getTime());
        newBlock.mineBlock(prefix);
        Assert.assertEquals(newBlock.getHash().substring(0, prefix), prefixString);
        blockchain.add(newBlock);
    }

    /**
     * Test whether we can validate the entire blockchain. There are specific checks for each block: we're testing whether
     * the stored hash of the current block is what is being calculated, whether the hash of the previous block being
     * stored in the current block really is the hash of the previous block, and whether the current block has been mined.
     */
    @Test
    public void givenBlockchain_whenValidated_thenSuccess() {
        boolean flag = true;
        for (int i = 0; i < blockchain.size(); ++i) {
            String lastHash = i == 0 ? "0" : blockchain.get(i - 1).getHash();
            flag = blockchain.get(i).getHash().equals(blockchain.get(i).calculateBlockHash()) && lastHash.equals(blockchain.get(i).getLastHash()) && blockchain.get(i).getHash().substring(0, prefix).equals(prefixString);
            if (!flag) break;
        }
        Assert.assertTrue(flag);
    }
}
