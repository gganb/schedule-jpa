package com.example.schedule_jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "schedule")
@Getter
@DynamicUpdate
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String title;
    private String contents;

    public Schedule(){}

    public Schedule(String username, String title, String contents) {
      this.username = username;
      this.title = title;
      this.contents = contents;
    }

    public void updateContents(String contents){
        this.contents = contents;
    }
    public void updateTitle(String title){
        this.title = title;
    }
    public void update(String title, String contents){
        this.title = title;
        this.contents = contents;
    }
}
