package org.voovan.docker.command.Service;

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
public class CmdServiceRemove extends Cmd {
    private String nameOrId ;

    public CmdServiceRemove(String nameOrId) {
        this.nameOrId = nameOrId;
    }



    public static CmdServiceRemove newInstance(String nameOrId){
        return new CmdServiceRemove(nameOrId);
    }

    @Override
    public String send() throws Exception {
        Result result = getDockerHttpClient().run("DELETE","/services/"+nameOrId,getParameters());
        if(result!=null && result.getStatus()>=300){
            throw new DockerClientException(result.getMessage());
        }else{
            return result.getMessage();
        }

    }

}
