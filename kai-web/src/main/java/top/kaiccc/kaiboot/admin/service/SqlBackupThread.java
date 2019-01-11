package top.kaiccc.kaiboot.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Script;

import java.sql.SQLException;

/**
 * @author kaiccc
 * @date 2019-01-07 14:36
 */
@Slf4j
public class SqlBackupThread extends Thread {

    private String tempPath;

    @Override
    public void run() {
        log.info("SqlBackupThread start ");
        Script script = new Script();
        try {
            script.process("jdbc:h2:E:/h2/database", "kai", "kaiccc", "E:\\test.zip", "compression", "zip");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
