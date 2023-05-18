package raf.webProgramiranje;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import raf.webProgramiranje.repositories.implementations.*;
import raf.webProgramiranje.repositories.specifications.*;
import raf.webProgramiranje.services.*;
import raf.webProgramiranje.services.implementations.CategoryServiceImpl;
import raf.webProgramiranje.services.implementations.CommentServiceImpl;
import raf.webProgramiranje.services.implementations.NewsServiceImpl;
import raf.webProgramiranje.services.implementations.UserServiceImpl;

@ApplicationPath("/api")
    public class Application extends ResourceConfig {
        public Application() {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
            AbstractBinder binder = new AbstractBinder() {
                @Override
                protected void configure() {
                    this.bind(UserRepositoryImpl.class).to(UserRepository.class).in(Singleton.class);
                   this.bind(CategoryRepositoryImpl.class).to(CategoryRepository.class).in(Singleton.class);
                    this.bind(NewsRepositoryImpl.class).to(NewsRepository.class).in(Singleton.class);
                   this.bind(TagRepositoryImpl.class).to(TagRepository.class).in(Singleton.class);
                   this.bind(NewsTagRepositoryImpl.class).to(NewsTagRepository.class).in(Singleton.class);
                   this.bind(CommentRepositoryImpl.class).to(CommentRepository.class).in(Singleton.class);


                   this.bindAsContract(CategoryServiceImpl.class).to(CategoryService.class).in(Singleton.class);
                   this.bindAsContract(NewsServiceImpl.class).to(NewsService.class).in(Singleton.class);
                   this.bindAsContract(CommentServiceImpl.class).to(CommentService.class).in(Singleton.class);
                    this.bindAsContract(UserServiceImpl.class).to(UserService.class).in(Singleton.class);
                }
            };
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
            register(binder);
            packages("raf.webProgramiranje");
        }
    }
