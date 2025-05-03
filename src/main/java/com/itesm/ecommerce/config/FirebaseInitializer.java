package com.itesm.ecommerce.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.FileInputStream;

@ApplicationScoped
@Startup
public class FirebaseInitializer {

    @PostConstruct
    public void init(){

        if(FirebaseApp.getApps().isEmpty()) {
            try {
                FileInputStream serviceAccount =
                        new FileInputStream("src/main/resources/ecommerce-dac8d-firebase-adminsdk-fbsvc-3845230258.json");
                FirebaseOptions options= FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
                FirebaseApp.initializeApp(options);
                System.out.println(serviceAccount);
                System.out.println("Firebase initialized successfully");
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error initializing Firebase", e);
            }
        }
    }

}
