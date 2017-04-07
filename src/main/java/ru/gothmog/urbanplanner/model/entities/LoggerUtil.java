package ru.gothmog.urbanplanner.model.entities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author d.grushetskiy
 */
public class LoggerUtil {
    public static void error(String message) {
        Logger logger = LogManager.getLogger("common");
        logger.error(message);
    }
}
