package com.korit.sinicare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="careRecords")
@Table(name="careRecords")
public class CareRecordEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer recordNumber;
    private String recordDate;
    private String contents;
    private Integer usedToolName;
    private Integer count;
    private String charger;
    private Integer customNumber;
}
