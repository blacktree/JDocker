package org.voovan.docker.command.Container;

import org.voovan.docker.command.Cmd;
import org.voovan.docker.network.DockerClientException;
import org.voovan.docker.network.Result;

/**
 * 类文字命名
 *
 * @author helyho
 *         <p>
 *         JDocker Framework.
 *         WebSite: https://github.com/helyho/JDocker
 *         Licence: Apache v2 License
 */
public class CmdContainerStop extends Cmd {
    private String nameOrId;

    public CmdContainerStop(String nameOrId) {
        this.nameOrId = nameOrId;
    }

    public CmdContainerStop waitForKill(int waitForKill){
        addParameter("t",waitForKill);
        return this;
    }

    public static CmdContainerStop newInstance(String nameOrId){
        return new CmdContainerStop(nameOrId);
    }

    @Override
    public String send() throws Exception {
        Result result = getDockerHttpClient().run("POST","/containers/"+nameOrId+"/stop", getParameters());
        if(result!=null && result.getStatus()>=300){
            throw new DockerClientException(result.getMessage());
        }else{
            return result.getMessage();
        }
    }
}
