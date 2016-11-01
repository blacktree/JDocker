package org.voovan.docker.client.Command.Service;

import org.voovan.docker.client.Command.Cmd;
import org.voovan.docker.client.network.DockerClientException;
import org.voovan.docker.client.network.Result;
import org.voovan.docker.message.service.ServiceInfo;
import org.voovan.docker.message.service.ServiceSpec;
import org.voovan.docker.message.service.atom.Mount;
import org.voovan.docker.message.service.atom.Network;
import org.voovan.docker.message.service.atom.Port;
import org.voovan.tools.TObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类文字命名
 *
 * @author helyho
 *         <p>
 *         JDocker Framework.
 *         WebSite: https://github.com/helyho/JDocker
 *         Licence: Apache v2 License
 */
public class CmdServiceCreate extends Cmd {

    private ServiceSpec serviceSpec;

    public CmdServiceCreate() {
        serviceSpec = new ServiceSpec();
    }



    public CmdServiceCreate name(String name){
        serviceSpec.setName(name);
        return this;
    }

    public CmdServiceCreate image(String image){
        serviceSpec.getTaskTemplate().getContainer().setImage(image);
        return this;
    }

    public CmdServiceCreate cmd(String ... cmd){
        serviceSpec.getTaskTemplate().getContainer().getArgs().addAll(TObject.newList(cmd));
        return this;
    }

    public CmdServiceCreate env(String ... env){
        serviceSpec.getTaskTemplate().getContainer().getEnv().addAll(TObject.newList(env));
        return this;
    }

    public CmdServiceCreate stopGracePeriod(long stopGracePeriod){
        serviceSpec.getTaskTemplate().getContainer().setStopGracePeriod(stopGracePeriod);
        return this;
    }


    public CmdServiceCreate cpuLimit(int nanoCpu){
        serviceSpec.getTaskTemplate().getResource().getLimits().setNanoCPUs(nanoCpu);
        return this;
    }

    public CmdServiceCreate memoryLimit(long memoryByte){
        serviceSpec.getTaskTemplate().getResource().getLimits().setMemoryBytes(memoryByte);
        return this;
    }

    public CmdServiceCreate cpuReservation(int nanoCpu){
        serviceSpec.getTaskTemplate().getResource().getReservations().setNanoCPUs(nanoCpu);
        return this;
    }

    public CmdServiceCreate memoryReservation(long memoryByte){
        serviceSpec.getTaskTemplate().getResource().getReservations().setMemoryBytes(memoryByte);
        return this;
    }


    public CmdServiceCreate network(String network){
        serviceSpec.getNetworks().add(new Network(network));
        return this;
    }

    public CmdServiceCreate mode(String mode){
        serviceSpec.getEndpoint().setMode(mode);
        return this;
    }


    public CmdServiceCreate  port(String protocol,int targetPort, int publishPort){
        serviceSpec.getEndpoint().getPorts().add(new Port(protocol,targetPort,publishPort));
        return this;
    }

    public CmdServiceCreate  portTcp(int targetPort, int publishPort){
        serviceSpec.getEndpoint().getPorts().add(new Port("tcp",targetPort,publishPort));
        return this;
    }

    public CmdServiceCreate  portUdp(int targetPort, int publishPort){
        serviceSpec.getEndpoint().getPorts().add(new Port("udp",targetPort,publishPort));
        return this;
    }


    public CmdServiceCreate mountRead(String source, String target){
        serviceSpec.getTaskTemplate().getContainer().getMounts().add(new Mount(source,target,true));
        return this;
    }

    public CmdServiceCreate mountReadWrite(String source, String target){
        serviceSpec.getTaskTemplate().getContainer().getMounts().add(new Mount(source,target,false));
        return this;
    }

    public CmdServiceCreate mount(String source, String target, boolean readOnly, String type){
        serviceSpec.getTaskTemplate().getContainer().getMounts().add(new Mount(source,target,readOnly, type));
        return this;
    }


    public CmdServiceCreate replicate(int replicas){
        serviceSpec.getMode().getReplicated().setReplicas(replicas);
        return this;
    }

    public CmdServiceCreate restartPolicy(long delay, String ondition, int maxAttempts){
        serviceSpec.getTaskTemplate().getRestartPolicy().setDelay(delay);
        serviceSpec.getTaskTemplate().getRestartPolicy().setCondition(ondition);
        serviceSpec.getTaskTemplate().getRestartPolicy().setMaxAttempts(maxAttempts);
        return this;
    }

    public CmdServiceCreate updateConfig(long delay, String failureAction, int parallelism){
        serviceSpec.getUpdateConfig().setDelay(delay);
        serviceSpec.getUpdateConfig().setFailureAction(failureAction);
        serviceSpec.getUpdateConfig().setParallelism(parallelism);
        return this;
    }


    public static CmdServiceCreate newInstance(){
        return new CmdServiceCreate();
    }

    @Override
    public Result send() throws Exception {
        Result result = getDockerHttpClient().post("/services/create",getParameters(),serviceSpec);
        if(result.getStatus()>=300){
            throw new DockerClientException(result);
        }else{
            return result;
        }

    }
}