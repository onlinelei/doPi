package doPi;

import com.google.common.collect.ImmutableList;
import org.bitcoinj.crypto.*;
import org.bitcoinj.wallet.DeterministicSeed;
import org.junit.jupiter.api.Test;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import sun.security.provider.SecureRandom;

import java.util.List;

public class EHTAddressGenerator extends DoPiApplicationTests {
  private final ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
      ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true),
          ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

  @Test
  public void contextLoads() throws MnemonicException.MnemonicLengthException {
    for (int i = 0; i < 10; i++) {
      createWallet();
    }
  }


  /**
   * 创建钱包
   *
   * @throws MnemonicException.MnemonicLengthException
   */
  public void createWallet() throws MnemonicException.MnemonicLengthException {
    SecureRandom secureRandom = new SecureRandom();
    byte[] entropy = new byte[DeterministicSeed.DEFAULT_SEED_ENTROPY_BITS / 8];
    secureRandom.engineNextBytes(entropy);

    //生成12位助记词
    List<String> str = MnemonicCode.INSTANCE.toMnemonic(entropy);

    //使用助记词生成钱包种子
    byte[] seed = MnemonicCode.toSeed(str, "");
    DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);

    DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
    DeterministicKey deterministicKey = deterministicHierarchy
        .deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));
    byte[] bytes = deterministicKey.getPrivKeyBytes();
    ECKeyPair keyPair = ECKeyPair.create(bytes);
    //通过公钥生成钱包地址
    String address = Keys.getAddress(keyPair.getPublicKey());

    System.out.println("助记词：" + str);
    System.out.println("地址：" + "0x" + address);
    System.out.println("私钥：" + "0x" + keyPair.getPrivateKey().toString(16));
    System.out.println("公钥：" + keyPair.getPublicKey().toString(16));
  }
}
