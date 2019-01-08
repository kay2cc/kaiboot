package top.kaiccc.kaiboot.common.license;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
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
    private static final String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJabz2+WjJtiNbmbZUstCJa4lb+3jKW83514l334oOHxGUBKemnK3+qV8jOE56N6c7pPvqt1e23u5GC3DjlHIsGWWsz4dTsTFp9aVzRcSokeFFjOO9N7+ZdXa0+lLDndeAU3PPTyAuxwyNe4ke5UA0ukv9fPWqDg6vV9QZP/sA2BAgMBAAECgYAHWNB0tBZl2hOe20rEsQyzNYvwj0aoKx+JcgGJ3qAShY1iyn9QO9pSekAXbUtTeTy+APffK1r7EOVJJoTmHGdAkKqYxti3/Dy+6xNlOa01uFOIS4dp302F8/85hy41cfzntD+1I5Sx0pZjRSp+cbL303XZV0s45p0GkrDXY9fk9QJBAPZ10q/1pIKoyKgqHRRDFiztQ5Tqzwn/f7FREDYIQbzpocC8ya6btZ1j3eH82tGOFyO4K8k0eeoPkAkqXlJps98CQQCccDFW30qTktIlYQkaMPnn1//CRKdXMz7YDh1KKrm7ojEVVi2493oldFybQ/3LNotZ5jdsXBtyVrJtpsceUWqfAkAC5Ut+acJvDpCc+TGyRT+Dc54xp7ibA3d6m9s2503ubEhMPWuZM5ckrEG1IAVqJOM8tjM9f/JWv6wPj4HgMgG9AkAQclMdmPgUL7lcKKl2UXPUhgbje6hEKKUQvdsicv931INohNjlu07iJSufPzStapyX5PYqtDxGdwrnMEN60oBtAkAb5EqsTo9VBs6iTBrHRutZjvw6Zc+dg9/25zSesuqXmZkYCaruTrkcUyxXTlPE1UtwpWBTDb+WwNk+lEtPHe/B";
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOdV+bzgLozsBrOGPHSq/T+SMQd5M3HjYTQGlTflje2C9bxC2MBFDCOSkgn4AYzfTAv0suf2KOBfahZdtY4IKflK+YRwJILrYIrxdgobknzMrw7a8CskGKUG0hzA7OiabiCXchdOElMWNHjwAACtEgpT89wqSacTUqNgln0dw86wIDAQAB";


    public static void main(String[] args) {
        String cpuCode = getCPUCode();
        String baseboardCode = getBaseboard();
        String hostInfo = Base64.encode(getHostInfo());

        String license = StrUtil.format("{}+{}+{}+{}", cpuCode, baseboardCode, System.currentTimeMillis(), hostInfo);

        log.info("cpu: {}, baseboard: {}", cpuCode, baseboardCode);
        log.info(hostInfo);
        log.info(license);

        /**
         * 客户端生成 License 文件规则：
         * cpu + baseboardCode + 时间戳 + hostInfo 转 base64_RSA
         *
         * 客户端激活 License 码规则：
         * base64(cpu + 激活码4-10位 + baseboardCode) 转 md5
         */

        RSA publicRea = new RSA(null, publicKey);

        //获得私钥
       /* String privateKey = rsa.getPrivateKeyBase64();
        log.info(privateKey);*/
        //获取公钥
//       String publicKey = rsa.getPublicKeyBase64();
//        log.info("publicKey: {}", publicKey);

        String encryptStr = publicRea.encryptBase64(license, CharsetUtil.CHARSET_UTF_8, KeyType.PublicKey);
        log.info(encryptStr);

        RSA rsa = new RSA(privateKey, null);

        byte[] decrypt = rsa.decryptFromBase64(encryptStr, KeyType.PrivateKey);
        log.info(new String(decrypt));


    }

    public static String getHostInfo(){
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
