package top.kaiccc.kaiboot.common.license;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.system.SystemUtil;

import java.io.IOException;
import java.util.Scanner;

public class LicenseUtils {
    private static final Log log = LogFactory.get();
    private static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALKRMiLgqxU6BSN74talKDp6hXjZ5msspG4eROlVrhPmxupk+1tLYG6RWc6ky/4edjrxEvteZFvA62NVMn3uLxkjpxKB0c5ruK20I45gNds2gmVEFrmRGfwbKLNxvuO+ahIGoeZ1WsK6zyU6QiFADLjPCpbiHcZMVhyqNOY/wl9JAgMBAAECgYAk9oVITvVbGX2C0NwtzlY7dK04TPKzcLRAzyc4nbHBEN5QGsMlLL8zZAcRDW/klG2o2coyGmHv1EllcHdZNfqtgeB80XKcS2UF66riPgfENa5lzLj5pT3NhEtXCyWbaDOQV8s32MMxOfj0exrrwFJTpfdFob/c2xFjqay4oD+EAQJBAPqGIX5Pxm88muWWcsQXDhPcFb6/X7df1g2dekx9GzTHgveQvPgdybL7Q7OxgU/5/wz/jRVSMuJkLPMIH7O7g8ECQQC2eGm2QxVYvNAaz7kgEGrUUyNq8gIip2oi9vzJrxwZyfNZk628SNLjx/veNjfJKQgHNKy126Rq6/We8AtBfB2JAkBvbgWrxxP+GjjTca2uZorObpAFfd23FJuDBs6FAwh821j3gJtsyOMUBwq//G4GAq5dO7WnDB6x79jGaJChQwCBAkEAnxucQht4r6nkZJygtj7aiE3+YjCogAMvX1Gy10Jj5HDeS0ukgLuXeJtXGkp4lmW65F2gA+uEEN+zi2WQywco0QJBAOq0wLalz7e9PBpel484AGglEqmhPrkeFpDlTyyTxbpUobewLNHodVeCPTj4usBbvLVj+4QMzNv6XYbTH8YJ2w4=";
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCykTIi4KsVOgUje+LWpSg6eoV42eZrLKRuHkTpVa4T5sbqZPtbS2BukVnOpMv+HnY68RL7XmRbwOtjVTJ97i8ZI6cSgdHOa7ittCOOYDXbNoJlRBa5kRn8Gyizcb7jvmoSBqHmdVrCus8lOkIhQAy4zwqW4h3GTFYcqjTmP8JfSQIDAQAB";


    public static void main(String[] args) throws IOException {
        String cpuCode = getCPUCode();
        String baseboardCode = getBaseboard();
        //String hostInfo = Base64.encode(getHostInfo());

        String license = StrUtil.format("{}+{}+{}", cpuCode, baseboardCode, System.currentTimeMillis());

        log.info("cpu: {}, baseboard: {}", cpuCode, baseboardCode);

        log.info(license);

        /**
         * 客户端生成 License 文件规则：
         * cpu + baseboardCode + 时间戳 + hostInfo 转 base64_RSA
         *
         * 客户端激活 License 码规则：
         * base64(cpu + 激活码4-10位 + baseboardCode) 转 md5
         */

        RSA publicRsa = new RSA(null, publicKey);

/*        //获得私钥
        String privateKey = rsa.getPrivateKeyBase64();
        log.info(privateKey);
        //获取公钥
        String publicKey = rsa.getPublicKeyBase64();
        log.info("publicKey: {}", publicKey);*/

        String encryptStr = publicRsa.encryptBase64(license, CharsetUtil.CHARSET_UTF_8, KeyType.PublicKey);
        log.info(encryptStr);

        RSA rsa = new RSA(privateKey, null);

        byte[] decrypt = rsa.decryptFromBase64(encryptStr, KeyType.PrivateKey);
        log.info(new String(decrypt));


        String salt = "HFSZUUGCVRAY";
        /**
         * 激活码
         */
        String random =  RandomUtil.randomString(4);
        String hostMd5 = StrUtil.format("{}+{}+{}", cpuCode, random, baseboardCode);
        log.info(hostMd5);

        String hexCode = HexUtil.encodeHexStr(cpuCode);
        String code = hexCode.substring(0,4) + random + hexCode.substring(6,10) + hexCode.substring(hexCode.length()-4);

        log.info(HexUtil.encodeHexStr(cpuCode));

        log.info(code);



        //ZipUtil.unGzip();


        /*String decEnc = aes.decrypt(aesEnc);
        log.info(decEnc);
*/
    }
    public static String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2){
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }


    public static String getHostInfo() {
        String osName = SystemUtil.getOsInfo().getName();
        String hostName = SystemUtil.getHostInfo().getName();
        String ip = SystemUtil.getHostInfo().getAddress();

        return StrUtil.format("{},{},{}", osName, ip, hostName);
    }

    /**
     * CPU序列号
     *
     * @return
     */
    public static String getCPUCode() {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"wmic", "cpu", "get", "ProcessorId"});
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            sc.next();
            return sc.next();
        } catch (IOException e) {
            log.error("生成CPUSerial失败", e);
        }
        return null;
    }

    /**
     * 主板序列号
     *
     * @return
     */
    public static String getBaseboard() {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"wmic", "baseboard", "get", "serialnumber"});
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            sc.next();
            return sc.next();
        } catch (IOException e) {
            log.error("生成主板序列号失败", e);
        }
        return null;
    }


}
