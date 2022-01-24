package com.kataacademy.schoolportal.common.models.schoolatribute;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "day_time_table")
public class DayTimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "day_time")
    private LocalDateTime dayTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "day_time_lessons",
            joinColumns = @JoinColumn(name = "day_time_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private Set<Lesson> lessons;

    public DayTimeTable(LocalDateTime dateTime, Set<Lesson> lessons) {
        this.dayTime = dateTime;
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "DayTimeTable{" +
                "id=" + id +
                ", dateTime=" + dayTime +
                ", lessons=" + lessons +
                '}';
    }
}
