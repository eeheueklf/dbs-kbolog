package com.example.kbolog.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@DiscriminatorValue("PITCHER")
public class Pitcher extends Player{

    @Column(nullable = false)
    private Double ip; // 이닝

    @Column(nullable = false)
    private Double era; // 평균자책점

    @Column(nullable = false)
    private Double whip; // 이닝당 출루 허용률

}
