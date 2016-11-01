package org.voovan.docker.command.Node;

import org.voovan.docker.command.Cmd;
import org.voovan.docker.message.Node.NodeInfo;
import org.voovan.docker.message.Node.NodeUpdate;
import org.voovan.docker.message.Task.TaskInfo;
import org.voovan.docker.network.DockerClientException;
import org.voovan.docker.network.Result;

import java.util.List;

/**
 * 类文字命名
 *
 * @author helyho
 *         <p>
 *         JDocker Framework.
 *         WebSite: https://github.com/helyho/JDocker
 *         Licence: Apache v2 License
 */
public class CmdNodeUpdate extends Cmd {

    private String id;
    private NodeUpdate nodeUpdate;

    public CmdNodeUpdate(String id) {
        this.id = id;
        this.nodeUpdate = new NodeUpdate();
    }

    public CmdNodeUpdate name(String name){
        nodeUpdate.setName(name);
        return this;
    }

    public CmdNodeUpdate role(String role){
        nodeUpdate.setRole(role);
        return this;
    }

    public CmdNodeUpdate availability(String availability){
        nodeUpdate.setAvailability(availability);
        return this;
    }

    public CmdNodeUpdate label(String key,String value){
        nodeUpdate.getLabels().put(key, value);
        return this;
    }


    public static CmdNodeUpdate newInstance(String id){
        return new CmdNodeUpdate(id);
    }

    @Override
    public List<TaskInfo> send() throws Exception {
        Result result = getDockerHttpClient().delete("/nodes/"+id,getParameters());
        if(result.getStatus()>=300){
            throw new DockerClientException(result);
        }else{
            return TaskInfo.load(result.getMessage());
        }

    }

}