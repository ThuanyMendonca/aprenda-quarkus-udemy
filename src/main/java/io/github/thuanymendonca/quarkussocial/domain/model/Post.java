package io.github.thuanymendonca.quarkussocial.domain.model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "post_text")
    private String text;
    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Antes de persistir no banco de dados
    @PrePersist
    public void prePersist(){
        setDateTime(LocalDateTime.now());
    }
}
