package org.voovan.docker.command.Container;

import org.voovan.docker.command.Cmd;
import org.voovan.docker.network.DockerClientException;
import org.voovan.docker.network.Result;
import org.voovan.docker.message.container.ContainerStats;

/**
 * 类文字命名
 *
 * @author helyho
 *         <p>
 *         JDocker Framework.
 *         WebSite: https://github.com/helyho/JDocker
 *         Licence: Apache v2 License
 */
public class CmdContainerStats extends Cmd {
    private String nameOrId;

    public CmdContainerStats(String nameOrId) {
        this.nameOrId = nameOrId;
        this.addParameter("stream",0);
    }


    public static CmdContainerStats newInstance(String nameOrId){
        return new CmdContainerStats(nameOrId);
    }

    @Override
    public ContainerStats send() throws Exception {
        Result result = getDockerHttpClient().get("/containers/"+nameOrId+"/stats", getParameters());
        if(result.getStatus()>=300){
            throw new DockerClientException(result);
        }else{
            return ContainerStats.load(result.getMessage());
        }
    }
}