package com.theodo.albeniz.configs;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("application.library")
public class LibraryProperties{
   @NonNull Integer maxCollection;
   @NonNull Boolean ascending;
}


