package wsdlhelper;

/**
 * Created by CSZ on 23.01.2018.
 */
public class Service {
    private String serviceName;
    private String serviceBinding;

    public Service(String name){
        this.serviceName = name;
    }

    public Service(String name, String binding){
        this.serviceName = name;
        this.serviceBinding = binding.replace(":", "_");
    }

    public String getServiceBinding() {
        return serviceBinding;
    }

    public void setServiceBinding(String serviceBinding) {
        this.serviceBinding = serviceBinding;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
