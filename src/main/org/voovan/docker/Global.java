package org.voovan.docker;

import org.voovan.docker.message.auth.AuthResult;

/**
 * 类文字命名
 *
 * @author helyho
 *<p>
 * JDocker Framework.
 * WebSite: https://github.com/helyho/JDocker
 * Licence: Apache v2 License
 */
public class Global {

    public static String DOCKER_REST_HOST = "";
    public static int DOCKER_REST_PORT = 2735;
    public static String DOCKER_REST_CHARSET = "UTF-8";
    public static int DOCKER_REST_TIMEOUT = 5;
    public static boolean DEBUG = false;

    public static AuthResult DOCKER_AUTH_RESULT = null;

}
