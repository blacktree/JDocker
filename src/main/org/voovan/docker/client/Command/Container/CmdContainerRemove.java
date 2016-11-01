package org.voovan.docker.client.Command.Container;

import org.voovan.docker.client.Command.Cmd;
import org.voovan.docker.client.network.DockerClientException;
import org.voovan.docker.client.network.Result;

/**
 * 类文字命名
 *
 * @author helyho
 *         <p>
 *         JDocker Framework.
 *         WebSite: https://github.com/helyho/JDocker
 *         Licence: Apache v2 License
 */
public class CmdContainerRemove extends Cmd{
    private String nameOrId;

    public CmdContainerRemove(String nameOrId) {
        this.nameOrId = nameOrId;
    }

    public static CmdContainerRemove newInstance(String nameOrId){
        return new CmdContainerRemove(nameOrId);
    }

    @Override
    public Result send() throws Exception {
        Result result = getDockerHttpClient().delete("/containers/"+nameOrId, getParameters());
        if(result.getStatus()>=300){
            throw new DockerClientException(result);
        }else{
            return result;
        }
    }
}