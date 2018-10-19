package com.indihx.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indihx.comm.util.BasicParameterInfo;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptionUtil {

	// 密钥方式
	public static final String ALGORITHM = "RSA";
	private static String path = new Object() {
		public String getPath() {
			return this.getClass().getResource("/").getPath();
		}
	}.getPath().substring(1);
	// 私钥路径
	public static final String PRIVATE_KEY_FILE = path + "\\key\\private.key";
	// 公钥路径
	public static final String PUBLIC_KEY_FILE = path + "\\key\\public.key";
	// 文件读取缓冲区大小
	// private static final int CACHE_SIZE = 1024;

	private static Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);

	/**
	 * 生成包含1024字节的私钥和公钥的密钥. 放到Prvate.key和Public.key文件中
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void generateKey() {
		try {
			// 得到RAR密钥对象
			final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
			keyGen.initialize(1024); // 初始化密钥大小为1024字节
			// 得到一个密钥实体
			final KeyPair key = keyGen.generateKeyPair();
			File privateKeyFile = new File(PRIVATE_KEY_FILE); // 私钥文件
			File publicKeyFile = new File(PUBLIC_KEY_FILE); // 公钥文件

			// 判断公钥和私钥文件是否存在，不存在则创建
			if (privateKeyFile.getParentFile() != null) {
				privateKeyFile.getParentFile().mkdirs();
			}
			privateKeyFile.createNewFile();
			if (publicKeyFile.getParentFile() != null) {
				publicKeyFile.getParentFile().mkdirs();
			}
			publicKeyFile.createNewFile();

			// 得到公钥保存到文件中
			ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
			publicKeyOS.writeObject(key.getPublic());
			publicKeyOS.close();
			String publicStr = fileToByte(PUBLIC_KEY_FILE);
			logger.info("公钥:" + publicStr);
			BasicParameterInfo.setLoginPublicKey(publicStr);
			// 得到私钥保存到文件中
			ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
			privateKeyOS.writeObject(key.getPrivate());
			privateKeyOS.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** */
	/**
	 * <p>
	 * 文件转换为二进制数组
	 * </p>
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static String fileToByte(String filePath) throws Exception {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath));
		final PublicKey publicKey = (PublicKey) inputStream.readObject();
		byte[] publicByte = publicKey.getEncoded();
		String publicStr = (new BASE64Encoder()).encodeBuffer(publicByte);
		return publicStr.replaceAll("[\\s*\t\n\r]", "");
	}

	/**
	 * 判断公钥和私钥的文件是否存在
	 * 
	 * @return 是否存在
	 */
	public static boolean areKeysPresent() {

		File privateKey = new File(PRIVATE_KEY_FILE);
		File publicKey = new File(PUBLIC_KEY_FILE);

		if (privateKey.exists() && publicKey.exists()) {
			try {
				String publicStr = fileToByte(PUBLIC_KEY_FILE);
				logger.info("公钥:" + publicStr);
				BasicParameterInfo.setLoginPublicKey(publicStr);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return true;
		}
		return false;
	}

	/**
	 * 根据公钥加密文本内容
	 * 
	 * @param text
	 *            需要加密的内容，纯文本
	 * @param key
	 *            :公钥
	 * @return 返回一个加密后的byte
	 * @throws java.lang.Exception
	 */
	@SuppressWarnings("resource")
	public static byte[] encrypt(String text) {
		byte[] cipherText = null;
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
			final PublicKey publicKey = (PublicKey) inputStream.readObject();
			// 得到一个RAR的密码对象
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			// 使用公钥加密的纯文本
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			cipherText = cipher.doFinal(text.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipherText;
	}

	/**
	 * 使用私钥解密文本内容
	 * 
	 * @param text
	 *            :encrypted text
	 * @param key
	 *            :The private key
	 * @return plain text
	 * @throws java.lang.Exception
	 */
	public static String decrypt(byte[] text, PrivateKey key) {
		byte[] dectyptedText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance(ALGORITHM);

			// decrypt the text using the private key
			cipher.init(Cipher.DECRYPT_MODE, key);
			dectyptedText = cipher.doFinal(text);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new String(dectyptedText);
	}

	/** */
	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static String decryptByPrivateKey(String data) {
		try {
			byte[] encryptedData = (new BASE64Decoder()).decodeBuffer(data);
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
			final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > 128) {
					cache = cipher.doFinal(encryptedData, offSet, 128);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * 128;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return new String(decryptedData, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Test the EncryptionUtil
	 */
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		try {
			if (!areKeysPresent()) {
				generateKey();
			}
			final String originalText = "中国！！";
			ObjectInputStream inputStream = null;

			// Encrypt the string using the public key
			inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
			final PublicKey publicKey = (PublicKey) inputStream.readObject();
			byte[] publicByte = publicKey.getEncoded();
			String publicStr = (new BASE64Encoder()).encodeBuffer(publicByte);
			System.out.println("公钥：" + publicStr.replaceAll("[\\s*\t\n\r]", ""));
			System.out.println(fileToByte(PUBLIC_KEY_FILE));
			byte[] cipherText = encrypt(originalText);
			String str = (new BASE64Encoder()).encode(cipherText).replaceAll("[\\s*\t\n\r]", "");

			System.out.println(cipherText + "--" + str);
			// Decrypt the cipher text using the private key.
			String mwe = "R7CDo9W78GKsKhhOF7GmapYbCIAN9WY1aczEUZ9ppINSTG6fzyjAoaSodqoby2yXSx5QEbesDQP9gcNJhinH60B7PWGYAheKLdMl5hdLnmG+kLrj5JLDHbFXv1EAIGHTkve+WNhn2pJAhdHft25fXxxWq5Tcy/pE9jSYxA5B3pM=";
			byte[] decodeStr = (new BASE64Decoder()).decodeBuffer(mwe);
			inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
			final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
			final String plainText = decryptByPrivateKey(str);
			// final String plainText = decrypt(mwe.getBytes(), privateKey);

			// Printing the Original , Encrypted and Decrypted Text
			System.out.println("Original: " + originalText);
			System.out.println("Encrypted: " + cipherText.toString());
			System.out.println("Decrypted: " + new String(plainText.getBytes(), "UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
