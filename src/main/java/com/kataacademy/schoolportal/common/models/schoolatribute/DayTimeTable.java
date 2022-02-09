package com.kataacademy.schoolportal.common.models.schoolatribute;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private LocalDate dayTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "day_time_lesson",
            joinColumns = @JoinColumn(name = "day_time_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private List<Lesson> lessons;

    public DayTimeTable(LocalDate dayTime, List<Lesson> lessons) {
        this.dayTime = dayTime;
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "DayTimeTable{" +
                "id=" + id +
                ", dayTime=" + dayTime +
                ", lessons=" + lessons +
                '}';
    }
}
