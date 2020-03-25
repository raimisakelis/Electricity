package lt.bta.java.api;



import lt.bta.java.api.impl.AuthorServiceImpl;
import lt.bta.java.api.impl.WorkServiceImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestApp extends Application {

    /**
     * Metodas, skirtas dirbti su objektu klasemis
     * @return set'as klasiu.
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(AuthorServiceImpl.class);
        resources.add(WorkServiceImpl.class);


        resources.add(ObjectMapperContextResolver.class);
        return resources;
    }
}

