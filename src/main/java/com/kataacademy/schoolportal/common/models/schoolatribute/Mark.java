package com.kataacademy.schoolportal.common.models.schoolatribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "marks")
public class Mark implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(2)
    @Max(5)
    private int mark;

    private LocalDate dayTime;

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", mark=" + mark +
                ", dayTime=" + dayTime +
                '}';
    }

}
