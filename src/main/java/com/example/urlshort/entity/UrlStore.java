package com.example.urlshort.entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UrlStore {

    @Id
    private String id;

    @NonNull
    private String url;

    private int hit;

    private LocalDateTime expiredTime;

    private boolean isActive;

    @UpdateTimestamp
    private LocalDateTime modifiedTime;
}
